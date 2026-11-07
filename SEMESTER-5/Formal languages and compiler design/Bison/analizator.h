#ifndef DEFS_H
#define DEFS_H

extern int num_lines, num_chars;
extern int errors;
void readFromFile();
int getCodAtom(char atom[]);
int addInTs(char atom[]);
void printFip();
void printTs();


#endif
