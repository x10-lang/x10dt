#ifndef __X10_ARRAY_RECTLAYOUT_H
#define __X10_ARRAY_RECTLAYOUT_H

#include <x10rt.h>


#define X10_LANG_ANY_H_NODEPS
#include <x10/lang/Any.h>
#undef X10_LANG_ANY_H_NODEPS
#define X10_LANG_ANY_H_NODEPS
#include <x10/lang/Any.h>
#undef X10_LANG_ANY_H_NODEPS
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace array { 
class Region;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace array { 
class Point;
} } 
namespace x10 { namespace compiler { 
class Header;
} } 
namespace x10 { namespace compiler { 
class Inline;
} } 
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
#include <x10/array/RectLayout.struct_h>

namespace x10 { namespace array { 

class RectLayout_methods  {
    public:
    static void _instance_init(x10::array::RectLayout& this_);
    
    static void _constructor(x10::array::RectLayout& this_, x10aux::ref<x10::array::Region> reg);
    
    inline static x10::array::RectLayout _make(x10aux::ref<x10::array::Region> reg)
    {
        x10::array::RectLayout this_; 
        _constructor(this_, reg);
        return this_;
    }
    
    static void _constructor(
      x10::array::RectLayout& this_, x10_int _min0,
      x10_int _max0)
    {
        {
            
            //#line 98 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RectLayout.x10": x10.ast.AssignPropertyCall_c
            this_->FMGL(rank) = ((x10_int)1);
            {
             
            }
        }
        
        //#line 99 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RectLayout.x10": polyglot.ast.Eval_c
        this_->
          FMGL(min0) =
          _min0;
        
        //#line 100 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RectLayout.x10": polyglot.ast.Eval_c
        this_->
          FMGL(delta0) =
          ((x10_int) ((((x10_int) ((_max0) - (_min0)))) + (((x10_int)1))));
        
        //#line 101 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RectLayout.x10": polyglot.ast.Eval_c
        this_->
          FMGL(size) =
          ((this_->
              FMGL(delta0)) > (((x10_int)0)))
          ? (x10_int)(this_->
                        FMGL(delta0))
          : (x10_int)(((x10_int)0));
        
        //#line 103 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RectLayout.x10": polyglot.ast.Eval_c
        this_->
          FMGL(min1) =
          ((x10_int)0);
        
        //#line 103 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RectLayout.x10": polyglot.ast.Eval_c
        this_->
          FMGL(delta1) =
          ((x10_int)0);
        
        //#line 104 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RectLayout.x10": polyglot.ast.Eval_c
        this_->
          FMGL(min2) =
          ((x10_int)0);
        
        //#line 104 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RectLayout.x10": polyglot.ast.Eval_c
        this_->
          FMGL(delta2) =
          ((x10_int)0);
        
        //#line 105 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RectLayout.x10": polyglot.ast.Eval_c
        this_->
          FMGL(min3) =
          ((x10_int)0);
        
        //#line 105 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RectLayout.x10": polyglot.ast.Eval_c
        this_->
          FMGL(delta3) =
          ((x10_int)0);
        
        //#line 106 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RectLayout.x10": polyglot.ast.Eval_c
        this_->
          FMGL(min) =
          x10aux::class_cast_unchecked<x10aux::ref<x10::array::Array<x10_int> > >(X10_NULL);
        
        //#line 106 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RectLayout.x10": polyglot.ast.Eval_c
        this_->
          FMGL(delta) =
          x10aux::class_cast_unchecked<x10aux::ref<x10::array::Array<x10_int> > >(X10_NULL);
        
    }
    inline static x10::array::RectLayout _make(
             x10_int _min0,
             x10_int _max0)
    {
        x10::array::RectLayout this_; 
        _constructor(this_, _min0,
        _max0);
        return this_;
    }
    
    static x10_int
      size(
      x10::array::RectLayout this_) {
        
        //#line 111 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RectLayout.x10": x10.ast.X10Return_c
        return this_->
                 FMGL(size);
        
    }
    static x10_int
      offset(
      x10::array::RectLayout this_, x10aux::ref<x10::array::Point> pt);
    static x10_int
      offset(
      x10::array::RectLayout this_, x10_int i0) {
        
        //#line 130 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RectLayout.x10": x10.ast.X10LocalDecl_c
        x10_int offset =
          ((x10_int) ((i0) - (this_->
                                FMGL(min0))));
        
        //#line 131 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RectLayout.x10": x10.ast.X10Return_c
        return offset;
        
    }
    static x10_int
      offset(
      x10::array::RectLayout this_, x10_int i0,
      x10_int i1) {
        
        //#line 135 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RectLayout.x10": x10.ast.X10LocalDecl_c
        x10_int offset =
          ((x10_int) ((i0) - (this_->
                                FMGL(min0))));
        
        //#line 136 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RectLayout.x10": polyglot.ast.Eval_c
        offset =
          ((x10_int) ((((x10_int) ((((x10_int) ((offset) * (this_->
                                                              FMGL(delta1))))) + (i1)))) - (this_->
                                                                                              FMGL(min1))));
        
        //#line 137 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RectLayout.x10": x10.ast.X10Return_c
        return offset;
        
    }
    static x10_int
      offset(
      x10::array::RectLayout this_, x10_int i0,
      x10_int i1,
      x10_int i2) {
        
        //#line 141 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RectLayout.x10": x10.ast.X10LocalDecl_c
        x10_int offset =
          ((x10_int) ((i0) - (this_->
                                FMGL(min0))));
        
        //#line 142 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RectLayout.x10": polyglot.ast.Eval_c
        offset =
          ((x10_int) ((((x10_int) ((((x10_int) ((offset) * (this_->
                                                              FMGL(delta1))))) + (i1)))) - (this_->
                                                                                              FMGL(min1))));
        
        //#line 143 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RectLayout.x10": polyglot.ast.Eval_c
        offset =
          ((x10_int) ((((x10_int) ((((x10_int) ((offset) * (this_->
                                                              FMGL(delta2))))) + (i2)))) - (this_->
                                                                                              FMGL(min2))));
        
        //#line 144 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RectLayout.x10": x10.ast.X10Return_c
        return offset;
        
    }
    static x10_int
      offset(
      x10::array::RectLayout this_, x10_int i0,
      x10_int i1,
      x10_int i2,
      x10_int i3) {
        
        //#line 148 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RectLayout.x10": x10.ast.X10LocalDecl_c
        x10_int offset =
          ((x10_int) ((i0) - (this_->
                                FMGL(min0))));
        
        //#line 149 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RectLayout.x10": polyglot.ast.Eval_c
        offset =
          ((x10_int) ((((x10_int) ((((x10_int) ((offset) * (this_->
                                                              FMGL(delta1))))) + (i1)))) - (this_->
                                                                                              FMGL(min1))));
        
        //#line 150 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RectLayout.x10": polyglot.ast.Eval_c
        offset =
          ((x10_int) ((((x10_int) ((((x10_int) ((offset) * (this_->
                                                              FMGL(delta2))))) + (i2)))) - (this_->
                                                                                              FMGL(min2))));
        
        //#line 151 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RectLayout.x10": polyglot.ast.Eval_c
        offset =
          ((x10_int) ((((x10_int) ((((x10_int) ((offset) * (this_->
                                                              FMGL(delta3))))) + (i3)))) - (this_->
                                                                                              FMGL(min3))));
        
        //#line 152 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RectLayout.x10": x10.ast.X10Return_c
        return offset;
        
    }
    static x10aux::ref<x10::lang::String>
      toString(
      x10::array::RectLayout this_);
    static x10_int
      rank(
      x10::array::RectLayout this_) {
        
        //#line 22 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RectLayout.x10": x10.ast.X10Return_c
        return this_->
                 FMGL(rank);
        
    }
    static x10_int
      hashCode(
      x10::array::RectLayout this_);
    static x10_boolean
      equals(
      x10::array::RectLayout this_, x10aux::ref<x10::lang::Any> other);
    static x10_boolean
      equals(
      x10::array::RectLayout this_, x10::array::RectLayout other) {
        
        //#line 22 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RectLayout.x10": x10.ast.X10Return_c
        return (x10aux::struct_equals(this_->
                                        FMGL(rank),
                                      other->
                                        FMGL(rank))) &&
        (x10aux::struct_equals(this_->
                                 FMGL(size),
                               other->
                                 FMGL(size))) &&
        (x10aux::struct_equals(this_->
                                 FMGL(min),
                               other->
                                 FMGL(min))) &&
        (x10aux::struct_equals(this_->
                                 FMGL(min0),
                               other->
                                 FMGL(min0))) &&
        (x10aux::struct_equals(this_->
                                 FMGL(min1),
                               other->
                                 FMGL(min1))) &&
        (x10aux::struct_equals(this_->
                                 FMGL(min2),
                               other->
                                 FMGL(min2))) &&
        (x10aux::struct_equals(this_->
                                 FMGL(min3),
                               other->
                                 FMGL(min3))) &&
        (x10aux::struct_equals(this_->
                                 FMGL(delta),
                               other->
                                 FMGL(delta))) &&
        (x10aux::struct_equals(this_->
                                 FMGL(delta0),
                               other->
                                 FMGL(delta0))) &&
        (x10aux::struct_equals(this_->
                                 FMGL(delta1),
                               other->
                                 FMGL(delta1))) &&
        (x10aux::struct_equals(this_->
                                 FMGL(delta2),
                               other->
                                 FMGL(delta2))) &&
        (x10aux::struct_equals(this_->
                                 FMGL(delta3),
                               other->
                                 FMGL(delta3)));
        
    }
    static x10_boolean
      _struct_equals(
      x10::array::RectLayout this_, x10aux::ref<x10::lang::Any> other);
    static x10_boolean
      _struct_equals(
      x10::array::RectLayout this_, x10::array::RectLayout other) {
        
        //#line 22 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RectLayout.x10": x10.ast.X10Return_c
        return (x10aux::struct_equals(this_->
                                        FMGL(rank),
                                      other->
                                        FMGL(rank))) &&
        (x10aux::struct_equals(this_->
                                 FMGL(size),
                               other->
                                 FMGL(size))) &&
        (x10aux::struct_equals(this_->
                                 FMGL(min),
                               other->
                                 FMGL(min))) &&
        (x10aux::struct_equals(this_->
                                 FMGL(min0),
                               other->
                                 FMGL(min0))) &&
        (x10aux::struct_equals(this_->
                                 FMGL(min1),
                               other->
                                 FMGL(min1))) &&
        (x10aux::struct_equals(this_->
                                 FMGL(min2),
                               other->
                                 FMGL(min2))) &&
        (x10aux::struct_equals(this_->
                                 FMGL(min3),
                               other->
                                 FMGL(min3))) &&
        (x10aux::struct_equals(this_->
                                 FMGL(delta),
                               other->
                                 FMGL(delta))) &&
        (x10aux::struct_equals(this_->
                                 FMGL(delta0),
                               other->
                                 FMGL(delta0))) &&
        (x10aux::struct_equals(this_->
                                 FMGL(delta1),
                               other->
                                 FMGL(delta1))) &&
        (x10aux::struct_equals(this_->
                                 FMGL(delta2),
                               other->
                                 FMGL(delta2))) &&
        (x10aux::struct_equals(this_->
                                 FMGL(delta3),
                               other->
                                 FMGL(delta3)));
        
    }
    static x10::array::RectLayout
      x10__array__RectLayout____x10__array__RectLayout__this(
      x10::array::RectLayout this_) {
        
        //#line 22 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RectLayout.x10": x10.ast.X10Return_c
        return this_;
        
    }
    
};

} } 
#endif // X10_ARRAY_RECTLAYOUT_H

namespace x10 { namespace array { 
class RectLayout;
} } 

#ifndef X10_ARRAY_RECTLAYOUT_H_NODEPS
#define X10_ARRAY_RECTLAYOUT_H_NODEPS
#include <x10/lang/Any.h>
#include <x10/lang/Any.h>
#include <x10/array/Array.h>
#include <x10/lang/Int.h>
#include <x10/array/Region.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/array/Point.h>
#include <x10/compiler/Header.h>
#include <x10/compiler/Inline.h>
#include <x10/lang/String.h>
#include <x10/compiler/Native.h>
#include <x10/compiler/NonEscaping.h>
#include <x10/lang/Boolean.h>
#ifndef X10_ARRAY_RECTLAYOUT_H_GENERICS
#define X10_ARRAY_RECTLAYOUT_H_GENERICS
#endif // X10_ARRAY_RECTLAYOUT_H_GENERICS
#endif // __X10_ARRAY_RECTLAYOUT_H_NODEPS
