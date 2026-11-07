#ifndef ASM_H
#define ASM_H

#define ADD_ASM_FORMAT "\
mov eax, %s \n\
add eax, %s \n\
mov %s, eax \n\
"

#define SUBTRACT_ASM_FORMAT "\
mov eax, %s \n\
sub eax, %s \n\
mov %s, eax \n\
"

#define MULTIPLY_ASM_FORMAT "\
mov eax, %s \n\
mov ebx, %s \n\
mov edx, 0 \n\
mul ebx \n\
mov dword [%s], eax \n\
"

#define DIVISION_ASM_FORMAT "\
mov eax, dword [%s] \n\
mov edx, 0\n\
mov ebx, %s \n\
div ebx \n\
mov %s, eax \n\
"

#define MODULO_ASM_FORMAT "\
mov eax, dword [%s] \n\
mov edx, 0 \n\
mov ebx, %s \n\
div ebx \n\
mov %s, edx \n\
"

#define PROGRAM_STRUCTURE "\
bits 32 \n\
\n\
global start \n\
\n\
; declararea functiilor externe folosite de program \n\
extern exit, printf, scanf ; adaugam printf si scanf ca functii externa \n\
import exit msvcrt.dll \n\
import printf msvcrt.dll    ; indicam asamblorului ca functia printf se gaseste in libraria msvcrt.dll \n\
import scanf msvcrt.dll     ; similar pentru scanf \n\
\n\
\n\
segment data use32 class=data \n\
%s \n\
\n\
\n\
segment code use32 class=code \n\
    start: \n\
    %s \n\
    \
    push dword 0\n\
    call [exit]\n\
"

#define CITIRE_VARIABILA "\
push dword %s \n\
push dword format \n\
call [scanf] \n\
add esp, 4*2 \n\
"


#define SCRIERE_VARIABILA "\
mov eax, %s\n\
push dword eax \n\
push dword format \n\
call [printf] \n\
add esp, 4*2 \n\
"

#endif
