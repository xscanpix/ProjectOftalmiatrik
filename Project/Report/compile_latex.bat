@ECHO off

rm "utkast till utkast till rapport.log"
rm "utkast till utkast till rapport.aux"
rm "utkast till utkast till rapport.pdf"

pdflatex "utkast till utkast till rapport.tex"
bibtex "utkast till utkast till rapport"
pdflatex "utkast till utkast till rapport.tex"
pdflatex "utkast till utkast till rapport.tex"
