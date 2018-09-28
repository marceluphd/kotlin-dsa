#!/bin/zsh -e

##################################################################################################
#
#   Copy datastructures & extensions from ~/Kotlin/DS+Algorithms/LeetCode/leetkode
#
#   Usage: copy-sublime-merge-settings [home/work]
#
##################################################################################################

source $ZDOTDIR/plugins/colors.zsh

echo "${PURPLE}\nRunning script:$ORANGE copy-extensions-and-ds.sh $NC"

leetkode_path="$HOME/Kotlin/DS+Algorithms/LeetCode/leetkode"
kotlin_dsa_path="$HOME/Code/Kotlin/DS+Algorithms/kotlin-dsa"

src_path="src/main/kotlin"
test_path="src/test/kotlin"

ds_path="datastructures"
exts_path="extensions"

# Delete existing
rm -rf "$kotlin_dsa_path/$src_path/$ds_path"
rm -rf "$kotlin_dsa_path/$src_path/$exts_path"
rm -rf "$kotlin_dsa_path/$test_path/$ds_path"
rm -rf "$kotlin_dsa_path/$test_path/$exts_path"

# Copy from leetkode
cp -R "$leetkode_path/$src_path/$ds_path" "$src_path/$ds_path"
cp -R "$leetkode_path/$src_path/$exts_path" "$src_path/$exts_path"
cp -R "$leetkode_path/$test_path/$ds_path" "$test_path/$ds_path"
cp -R "$leetkode_path/$test_path/$exts_path" "$test_path/$exts_path"

echo "${GREEN}Copied successfully.$NC"
