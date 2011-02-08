#ifndef __X10_UTIL_TEAM__DOUBLEIDX_H
#define __X10_UTIL_TEAM__DOUBLEIDX_H

#include <x10rt.h>


#define X10_LANG_ANY_H_NODEPS
#include <x10/lang/Any.h>
#undef X10_LANG_ANY_H_NODEPS
#define X10_LANG_ANY_H_NODEPS
#include <x10/lang/Any.h>
#undef X10_LANG_ANY_H_NODEPS
namespace x10 { namespace lang { 
class Double;
} } 
#include <x10/lang/Double.struct_h>
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
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
class Boolean;
} } 
#include <x10/lang/Boolean.struct_h>
#include <x10/util/Team__DoubleIdx.struct_h>

namespace x10 { namespace util { 

class Team__DoubleIdx_methods  {
    public:
    static void _instance_init(x10::util::Team__DoubleIdx& this_);
    
    static x10_double value(x10::util::Team__DoubleIdx this_) {
        
        //#line 30 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": x10.ast.X10Return_c
        return this_->FMGL(value);
        
    }
    static x10_int idx(x10::util::Team__DoubleIdx this_) {
        
        //#line 30 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": x10.ast.X10Return_c
        return this_->FMGL(idx);
        
    }
    static x10aux::ref<x10::lang::String> toString(x10::util::Team__DoubleIdx this_);
    static x10_int hashCode(x10::util::Team__DoubleIdx this_);
    static x10_boolean equals(x10::util::Team__DoubleIdx this_, x10aux::ref<x10::lang::Any> other);
    static x10_boolean equals(x10::util::Team__DoubleIdx this_, x10::util::Team__DoubleIdx other) {
        
        //#line 30 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": x10.ast.X10Return_c
        return (x10aux::struct_equals(this_->FMGL(value), other->FMGL(value))) &&
        (x10aux::struct_equals(this_->
                                 FMGL(idx),
                               other->
                                 FMGL(idx)));
        
    }
    static x10_boolean _struct_equals(x10::util::Team__DoubleIdx this_, x10aux::ref<x10::lang::Any> other);
    static x10_boolean _struct_equals(x10::util::Team__DoubleIdx this_, x10::util::Team__DoubleIdx other) {
        
        //#line 30 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": x10.ast.X10Return_c
        return (x10aux::struct_equals(this_->FMGL(value), other->
                                                            FMGL(value))) &&
        (x10aux::struct_equals(this_->
                                 FMGL(idx),
                               other->
                                 FMGL(idx)));
        
    }
    static x10::util::Team__DoubleIdx x10__util__Team__DoubleIdx____x10__util__Team__DoubleIdx__this(
      x10::util::Team__DoubleIdx this_) {
        
        //#line 30 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": x10.ast.X10Return_c
        return this_;
        
    }
    static void _constructor(x10::util::Team__DoubleIdx& this_, x10_double value,
                             x10_int idx) {
        {
            
            //#line 30 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": x10.ast.AssignPropertyCall_c
            this_->FMGL(value) = value;
            this_->FMGL(idx) = idx;
            {
             
            }
        }
        
    }
    inline static x10::util::Team__DoubleIdx _make(
             x10_double value,
             x10_int idx) {
        x10::util::Team__DoubleIdx this_; 
        _constructor(this_, value, idx);
        return this_;
    }
    
    
};

} } 
#endif // X10_UTIL_TEAM__DOUBLEIDX_H

namespace x10 { namespace util { 
class Team__DoubleIdx;
} } 

#ifndef X10_UTIL_TEAM__DOUBLEIDX_H_NODEPS
#define X10_UTIL_TEAM__DOUBLEIDX_H_NODEPS
#include <x10/lang/Any.h>
#include <x10/lang/Any.h>
#include <x10/lang/Double.h>
#include <x10/lang/Int.h>
#include <x10/lang/String.h>
#include <x10/compiler/Native.h>
#include <x10/compiler/NonEscaping.h>
#include <x10/lang/Boolean.h>
#ifndef X10_UTIL_TEAM__DOUBLEIDX_H_GENERICS
#define X10_UTIL_TEAM__DOUBLEIDX_H_GENERICS
#endif // X10_UTIL_TEAM__DOUBLEIDX_H_GENERICS
#endif // __X10_UTIL_TEAM__DOUBLEIDX_H_NODEPS
