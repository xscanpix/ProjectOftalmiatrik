@ECHO off

rm "report_draft.pdf"
rm "report_draft.aux"
rm "report_draft.blg"
rm "report_draft.bbl"
rm "report_draft.log"
rm "report_draft.toc"

pdflatex "report_draft.tex"
bibtex "report_draft"
pdflatex "report_draft.tex"
pdflatex "report_draft.tex"
