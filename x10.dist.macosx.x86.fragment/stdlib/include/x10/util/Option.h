#ifndef __X10_UTIL_OPTION_H
#define __X10_UTIL_OPTION_H

#include <x10rt.h>


#define X10_LANG_ANY_H_NODEPS
#include <x10/lang/Any.h>
#undef X10_LANG_ANY_H_NODEPS
#define X10_LANG_ANY_H_NODEPS
#include <x10/lang/Any.h>
#undef X10_LANG_ANY_H_NODEPS
namespace x10 { namespace lang { 
class String;
} } 
namespace x10 { namespace compiler { 
class Native;
} } 
namespace x10 { namespace compiler { 
class NonEscaping;
} } 
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace lang { 
class Boolean;
} } 
#include <x10/lang/Boolean.struct_h>
#include <x10/util/Option.struct_h>

namespace x10 { namespace util { 

class Option_methods  {
    public:
    static void _instance_init(x10::util::Option& this_);
    
    static void _constructor(x10::util::Option& this_, x10aux::ref<x10::lang::String> s,
                             x10aux::ref<x10::lang::String> l,
                             x10aux::ref<x10::lang::String> d);
    
    inline static x10::util::Option _make(x10aux::ref<x10::lang::String> s,
                                          x10aux::ref<x10::lang::String> l,
                                          x10aux::ref<x10::lang::String> d)
    {
        x10::util::Option this_; 
        _constructor(this_, s,
        l,
        d);
        return this_;
    }
    
    static x10aux::ref<x10::lang::String>
      toString(
      x10::util::Option this_) {
        
        //#line 25 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Option.x10": x10.ast.X10Return_c
        return this_->
                 FMGL(description);
        
    }
    static x10_int
      hashCode(
      x10::util::Option this_);
    static x10_boolean
      equals(
      x10::util::Option this_, x10aux::ref<x10::lang::Any> other);
    static x10_boolean
      equals(
      x10::util::Option this_, x10::util::Option other) {
        
        //#line 18 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Option.x10": x10.ast.X10Return_c
        return (x10aux::struct_equals(this_->
                                        FMGL(short_),
                                      other->
                                        FMGL(short_))) &&
        (x10aux::struct_equals(this_->
                                 FMGL(long_),
                               other->
                                 FMGL(long_))) &&
        (x10aux::struct_equals(this_->
                                 FMGL(description),
                               other->
                                 FMGL(description)));
        
    }
    static x10_boolean
      _struct_equals(
      x10::util::Option this_, x10aux::ref<x10::lang::Any> other);
    static x10_boolean
      _struct_equals(
      x10::util::Option this_, x10::util::Option other) {
        
        //#line 18 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Option.x10": x10.ast.X10Return_c
        return (x10aux::struct_equals(this_->
                                        FMGL(short_),
                                      other->
                                        FMGL(short_))) &&
        (x10aux::struct_equals(this_->
                                 FMGL(long_),
                               other->
                                 FMGL(long_))) &&
        (x10aux::struct_equals(this_->
                                 FMGL(description),
                               other->
                                 FMGL(description)));
        
    }
    static x10::util::Option
      x10__util__Option____x10__util__Option__this(
      x10::util::Option this_) {
        
        //#line 18 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Option.x10": x10.ast.X10Return_c
        return this_;
        
    }
    
};

} } 
#endif // X10_UTIL_OPTION_H

namespace x10 { namespace util { 
class Option;
} } 

#ifndef X10_UTIL_OPTION_H_NODEPS
#define X10_UTIL_OPTION_H_NODEPS
#include <x10/lang/Any.h>
#include <x10/lang/Any.h>
#include <x10/lang/String.h>
#include <x10/compiler/Native.h>
#include <x10/compiler/NonEscaping.h>
#include <x10/lang/Int.h>
#include <x10/lang/Boolean.h>
#ifndef X10_UTIL_OPTION_H_GENERICS
#define X10_UTIL_OPTION_H_GENERICS
#endif // X10_UTIL_OPTION_H_GENERICS
#endif // __X10_UTIL_OPTION_H_NODEPS
