@ECHO off

rm "utkast till utkast till rapport.pdf"
rm "utkast till utkast till rapport.aux"
rm "utkast till utkast till rapport.blg"
rm "utkast till utkast till rapport.bbl"
rm "utkast till utkast till rapport.log"

pdflatex "utkast till utkast till rapport.tex"
bibtex "utkast till utkast till rapport"
pdflatex "utkast till utkast till rapport.tex"
pdflatex "utkast till utkast till rapport.tex"
