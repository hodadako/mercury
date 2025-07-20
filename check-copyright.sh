#!/bin/bash

# Copyright check script for Mercury project
# Checks if all Java files contain the required copyright comment

set -e

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Required copyright text
COPYRIGHT_TEXT="Copyright (c) 2025 mercury contributors"
LICENSE_TEXT="This program is made available under the terms of the Apache License."

# Directories to check
JAVA_DIRS=("core/src/main/java" "core/src/test/java")

# Counters
TOTAL_FILES=0
FILES_WITH_COPYRIGHT=0
FILES_WITHOUT_COPYRIGHT=0
ERROR_FILES=()

echo "🔍 Checking copyright comments in Java files..."
echo "Required copyright: $COPYRIGHT_TEXT"
echo "Required license: $LICENSE_TEXT"
echo ""

# Function to check a single file
check_file() {
    local file="$1"
    local has_copyright=false
    local has_license=false
    
    # Check if file contains copyright text
    if grep -q "$COPYRIGHT_TEXT" "$file"; then
        has_copyright=true
    fi
    
    # Check if file contains license text
    if grep -q "$LICENSE_TEXT" "$file"; then
        has_license=true
    fi
    
    if [ "$has_copyright" = true ] && [ "$has_license" = true ]; then
        echo -e "${GREEN}✅${NC} $file"
        ((FILES_WITH_COPYRIGHT++))
    else
        echo -e "${RED}❌${NC} $file"
        if [ "$has_copyright" = false ]; then
            echo -e "   ${YELLOW}Missing:${NC} $COPYRIGHT_TEXT"
        fi
        if [ "$has_license" = false ]; then
            echo -e "   ${YELLOW}Missing:${NC} $LICENSE_TEXT"
        fi
        ERROR_FILES+=("$file")
        ((FILES_WITHOUT_COPYRIGHT++))
    fi
}

# Check each directory
for dir in "${JAVA_DIRS[@]}"; do
    if [ -d "$dir" ]; then
        echo "📁 Checking directory: $dir"
        while IFS= read -r -d '' file; do
            ((TOTAL_FILES++))
            check_file "$file"
        done < <(find "$dir" -name "*.java" -type f -print0)
        echo ""
    else
        echo -e "${YELLOW}⚠️  Directory not found: $dir${NC}"
    fi
done

# Summary
echo "📊 Summary:"
echo "Total Java files: $TOTAL_FILES"
echo -e "Files with copyright: ${GREEN}$FILES_WITH_COPYRIGHT${NC}"
echo -e "Files without copyright: ${RED}$FILES_WITHOUT_COPYRIGHT${NC}"

# Exit with error if any files are missing copyright
if [ ${#ERROR_FILES[@]} -gt 0 ]; then
    echo ""
    echo -e "${RED}❌ Found ${#ERROR_FILES[@]} files missing required copyright comments${NC}"
    echo ""
    echo "Files that need to be fixed:"
    for file in "${ERROR_FILES[@]}"; do
        echo "  - $file"
    done
    echo ""
    echo "To fix, add the following comment block at the top of each file:"
    echo "/*"
    echo " * Copyright (c) 2025 mercury contributors"
    echo " * This program is made available under the terms of the Apache License."
    echo " */"
    exit 1
else
    echo ""
    echo -e "${GREEN}🎉 All Java files have the required copyright comments!${NC}"
    exit 0
fi 