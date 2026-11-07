#ifndef ATTRIB_H
#define ATTRIB_H

#define VARN_SIZE 10
#define COD_SIZE 2500

struct attrib {
    char varn[VARN_SIZE];
    char cod[COD_SIZE];
};


typedef struct attrib attributes;

#endif
