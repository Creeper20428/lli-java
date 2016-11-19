; ModuleID = '/tmp/webcompile/_7543_0.bc'
target datalayout = "e-p:64:64:64-i1:8:8-i8:8:8-i16:16:16-i32:32:32-i64:64:64-f32:32:32-f64:64:64-v64:64:64-v128:128:128-a0:0:64-s0:64:64-f80:128:128-n8:16:32:64"
target triple = "x86_64-linux-gnu"

@.str = private constant [12 x i8] c"Hello LLVM!\0A\00", align 1 ; <[12 x i8]*> [#uses=1]

define i32 @main(i32 %argc, i8** nocapture %argv) nounwind {
entry:
  %0 = tail call i32 @puts(i8* getelementptr inbounds ([12 x i8]* @.str, i64 0, i64 2)) nounwind ; <i32> [#uses=0]
  ret i32 42
}

declare i32 @puts(i8* nocapture) nounwind
