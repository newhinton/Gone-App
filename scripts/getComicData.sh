#!/bin/bash


# wget -r --no-parent https://www.blastwave-comic.com/comics/
mkdir -p ../app/src/main/assets/comics
base='https://www.blastwave-comic.com/index.php?p=comic&nro='

echo "{\"comics\": [" > ../app/src/main/assets/metadata.json

# base:
for i in $(seq 1 92);
do
    # https://unix.stackexchange.com/a/388196
    html=$( curl -# -L "$base$i" 2> '/dev/null' )
    title=$(
        <<< "${html}" \
        grep -P -o -e '(?<=<div class="comic_title">)(.*?)(?=<\/div>)' |
        head -n 1
    )
    img=$(
        <<< "${html}" \
        grep -P -o -e '(?<=<img src="comics/)(.*?)(?="  alt)' |
        head -n 1
    )
    url=https://www.blastwave-comic.com/comics/$img
    echo "{\"id\": $i, \"title\": \"$title\", \"url\": \"$url\", \"img\": \"$img\"}," >> ../app/src/main/assets/metadata.json
#    wget "$url" -P ../app/src/main/assets/comics/
done


echo "]}" >> ../app/src/main/assets/metadata.json
