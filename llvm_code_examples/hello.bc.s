	.file	"hello.bc"
	.text
	.globl	main
	.align	16, 0x90
	.type	main,@function
main:                                   # @main
# BB#0:                                 # %entry
	subl	$4, %esp
	movl	$.L.str, (%esp)
	call	puts
	xorl	%eax, %eax
	addl	$4, %esp
	ret
	.size	main, .-main

	.type	.L.str,@object
	.section	.rodata.str1.1,"aMS",@progbits,1
.L.str:                                 # @.str
	.asciz	 "Hello LLVM!"
	.size	.L.str, 12


	.section	.note.GNU-stack,"",@progbits
