#ifndef __X10_LANG_COMPLEX_H
#define __X10_LANG_COMPLEX_H

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
class Math;
} } 
namespace x10 { namespace lang { 
class Boolean;
} } 
#include <x10/lang/Boolean.struct_h>
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
#include <x10/lang/Complex.struct_h>

namespace x10 { namespace lang { 

class Complex_methods  {
    public:
    static void _instance_init(x10::lang::Complex& this_);
    
    static x10::lang::Complex FMGL(ZERO);
    
    static void FMGL(ZERO__do_init)();
    static void FMGL(ZERO__init)();
    static volatile x10aux::status FMGL(ZERO__status);
    static inline x10::lang::Complex FMGL(ZERO__get)() {
        if (FMGL(ZERO__status) != x10aux::INITIALIZED) {
            FMGL(ZERO__init)();
        }
        return x10::lang::Complex_methods::FMGL(ZERO);
    }
    static x10aux::ref<x10::lang::Reference> FMGL(ZERO__deserialize)(x10aux::deserialization_buffer &buf);
    static const x10aux::serialization_id_t FMGL(ZERO__id);
    
    static x10::lang::Complex FMGL(ONE);
    
    static void FMGL(ONE__do_init)();
    static void FMGL(ONE__init)();
    static volatile x10aux::status FMGL(ONE__status);
    static inline x10::lang::Complex FMGL(ONE__get)() {
        if (FMGL(ONE__status) != x10aux::INITIALIZED) {
            FMGL(ONE__init)();
        }
        return x10::lang::Complex_methods::FMGL(ONE);
    }
    static x10aux::ref<x10::lang::Reference> FMGL(ONE__deserialize)(x10aux::deserialization_buffer &buf);
    static const x10aux::serialization_id_t FMGL(ONE__id);
    
    static x10::lang::Complex FMGL(I);
    
    static void FMGL(I__do_init)();
    static void FMGL(I__init)();
    static volatile x10aux::status FMGL(I__status);
    static inline x10::lang::Complex FMGL(I__get)() {
        if (FMGL(I__status) != x10aux::INITIALIZED) {
            FMGL(I__init)();
        }
        return x10::lang::Complex_methods::FMGL(I);
    }
    static x10aux::ref<x10::lang::Reference> FMGL(I__deserialize)(x10aux::deserialization_buffer &buf);
    static const x10aux::serialization_id_t FMGL(I__id);
    
    static x10::lang::Complex FMGL(INF);
    
    static void FMGL(INF__do_init)();
    static void FMGL(INF__init)();
    static volatile x10aux::status FMGL(INF__status);
    static inline x10::lang::Complex FMGL(INF__get)() {
        if (FMGL(INF__status) != x10aux::INITIALIZED) {
            FMGL(INF__init)();
        }
        return x10::lang::Complex_methods::FMGL(INF);
    }
    static x10aux::ref<x10::lang::Reference> FMGL(INF__deserialize)(x10aux::deserialization_buffer &buf);
    static const x10aux::serialization_id_t FMGL(INF__id);
    
    static x10::lang::Complex FMGL(NaN);
    
    static void FMGL(NaN__do_init)();
    static void FMGL(NaN__init)();
    static volatile x10aux::status FMGL(NaN__status);
    static inline x10::lang::Complex FMGL(NaN__get)() {
        if (FMGL(NaN__status) != x10aux::INITIALIZED) {
            FMGL(NaN__init)();
        }
        return x10::lang::Complex_methods::FMGL(NaN);
    }
    static x10aux::ref<x10::lang::Reference> FMGL(NaN__deserialize)(x10aux::deserialization_buffer &buf);
    static const x10aux::serialization_id_t FMGL(NaN__id);
    
    static void _constructor(x10::lang::Complex& this_, x10_double real, x10_double imaginary)
    {
        {
         
        }
        
        //#line 44 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Complex.x10": polyglot.ast.Eval_c
        this_->
          FMGL(re) =
          real;
        
        //#line 45 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Complex.x10": polyglot.ast.Eval_c
        this_->
          FMGL(im) =
          imaginary;
        
    }
    inline static x10::lang::Complex _make(
             x10_double real,
             x10_double imaginary)
    {
        x10::lang::Complex this_; 
        _constructor(this_, real,
        imaginary);
        return this_;
    }
    
    static x10::lang::Complex
      __plus(
      x10::lang::Complex this_, x10::lang::Complex that) {
        
        //#line 52 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Complex.x10": x10.ast.X10Return_c
        return x10::lang::Complex_methods::_make(((this_->
                                                     FMGL(re)) + (that->
                                                                    FMGL(re))),
                                                 ((this_->
                                                     FMGL(im)) + (that->
                                                                    FMGL(im))));
        
    }
    static x10::lang::Complex
      __plus(
      x10_double x,
      x10::lang::Complex y) {
        
        //#line 58 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Complex.x10": x10.ast.X10Return_c
        return x10::lang::Complex_methods::__plus(y, 
                 x);
        
    }
    static x10::lang::Complex
      __plus(
      x10::lang::Complex this_, x10_double that) {
        
        //#line 64 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Complex.x10": x10.ast.X10Return_c
        return x10::lang::Complex_methods::_make(((this_->
                                                     FMGL(re)) + (that)),
                                                 this_->
                                                   FMGL(im));
        
    }
    static x10::lang::Complex
      __minus(
      x10::lang::Complex this_, x10::lang::Complex that) {
        
        //#line 71 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Complex.x10": x10.ast.X10Return_c
        return x10::lang::Complex_methods::_make(((this_->
                                                     FMGL(re)) - (that->
                                                                    FMGL(re))),
                                                 ((this_->
                                                     FMGL(im)) - (that->
                                                                    FMGL(im))));
        
    }
    static x10::lang::Complex
      __minus(
      x10_double x,
      x10::lang::Complex y) {
        
        //#line 77 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Complex.x10": x10.ast.X10Return_c
        return x10::lang::Complex_methods::_make(((x) - (y->
                                                           FMGL(re))),
                                                 (-(y->
                                                      FMGL(im))));
        
    }
    static x10::lang::Complex
      __minus(
      x10::lang::Complex this_, x10_double that) {
        
        //#line 83 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Complex.x10": x10.ast.X10Return_c
        return x10::lang::Complex_methods::_make(((this_->
                                                     FMGL(re)) - (that)),
                                                 this_->
                                                   FMGL(im));
        
    }
    static x10::lang::Complex
      __times(
      x10::lang::Complex this_, x10::lang::Complex that) {
        
        //#line 90 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Complex.x10": x10.ast.X10Return_c
        return x10::lang::Complex_methods::_make(((((this_->
                                                       FMGL(re)) * (that->
                                                                      FMGL(re)))) - (((this_->
                                                                                         FMGL(im)) * (that->
                                                                                                        FMGL(im))))),
                                                 ((((this_->
                                                       FMGL(re)) * (that->
                                                                      FMGL(im)))) + (((this_->
                                                                                         FMGL(im)) * (that->
                                                                                                        FMGL(re))))));
        
    }
    static x10::lang::Complex
      __times(
      x10_double x,
      x10::lang::Complex y) {
        
        //#line 97 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Complex.x10": x10.ast.X10Return_c
        return x10::lang::Complex_methods::__times(y, 
                 x);
        
    }
    static x10::lang::Complex
      __times(
      x10::lang::Complex this_, x10_double that) {
        
        //#line 103 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Complex.x10": x10.ast.X10Return_c
        return x10::lang::Complex_methods::_make(((this_->
                                                     FMGL(re)) * (that)),
                                                 ((this_->
                                                     FMGL(im)) * (that)));
        
    }
    static x10::lang::Complex
      __over(
      x10::lang::Complex this_, x10::lang::Complex that);
    static x10::lang::Complex
      __over(
      x10_double x,
      x10::lang::Complex y) {
        
        //#line 149 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Complex.x10": x10.ast.X10Return_c
        return x10::lang::Complex_methods::__over(x10::lang::Complex_methods::_make(x,
                                                                                    0.0), 
                 y);
        
    }
    static x10::lang::Complex
      __over(
      x10::lang::Complex this_, x10_double that) {
        
        //#line 155 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Complex.x10": x10.ast.X10Return_c
        return x10::lang::Complex_methods::_make(((this_->
                                                     FMGL(re)) / (that)),
                                                 ((this_->
                                                     FMGL(im)) / (that)));
        
    }
    static x10::lang::Complex
      conjugate(
      x10::lang::Complex this_) {
        
        //#line 161 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Complex.x10": x10.ast.X10Return_c
        return x10::lang::Complex_methods::_make(this_->
                                                   FMGL(re),
                                                 (-(this_->
                                                      FMGL(im))));
        
    }
    static x10::lang::Complex
      __plus(
      x10::lang::Complex this_) {
        
        //#line 166 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Complex.x10": x10.ast.X10Return_c
        return this_;
        
    }
    static x10::lang::Complex
      __minus(
      x10::lang::Complex this_) {
        
        //#line 171 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Complex.x10": x10.ast.X10Return_c
        return x10::lang::Complex_methods::isNaN(this_)
          ? (x10::lang::Complex)(x10::lang::Complex_methods::
                                   FMGL(NaN__get)())
          : (x10::lang::Complex)(x10::lang::Complex_methods::_make((-(this_->
                                                                        FMGL(re))),
                                                                   (-(this_->
                                                                        FMGL(im)))));
        
    }
    static x10_double
      abs(
      x10::lang::Complex this_);
    static x10_boolean
      isNaN(
      x10::lang::Complex this_) {
        
        //#line 206 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Complex.x10": x10.ast.X10Return_c
        return x10aux::double_utils::isNaN(this_->
                                             FMGL(re)) ||
        x10aux::double_utils::isNaN(this_->
                                      FMGL(im));
        
    }
    static x10_boolean
      isInfinite(
      x10::lang::Complex this_) {
        
        //#line 214 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Complex.x10": x10.ast.X10Return_c
        return !(x10::lang::Complex_methods::isNaN(this_)) &&
        (x10aux::double_utils::isInfinite(this_->
                                            FMGL(re)) ||
         x10aux::double_utils::isInfinite(this_->
                                            FMGL(im)));
        
    }
    static x10aux::ref<x10::lang::String>
      toString(
      x10::lang::Complex this_);
    static x10_int
      hashCode(
      x10::lang::Complex this_);
    static x10_boolean
      equals(
      x10::lang::Complex this_, x10aux::ref<x10::lang::Any> other);
    static x10_boolean
      equals(
      x10::lang::Complex this_, x10::lang::Complex other) {
        
        //#line 19 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Complex.x10": x10.ast.X10Return_c
        return (x10aux::struct_equals(this_->
                                        FMGL(re),
                                      other->
                                        FMGL(re))) &&
        (x10aux::struct_equals(this_->
                                 FMGL(im),
                               other->
                                 FMGL(im)));
        
    }
    static x10_boolean
      _struct_equals(
      x10::lang::Complex this_, x10aux::ref<x10::lang::Any> other);
    static x10_boolean
      _struct_equals(
      x10::lang::Complex this_, x10::lang::Complex other) {
        
        //#line 19 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Complex.x10": x10.ast.X10Return_c
        return (x10aux::struct_equals(this_->
                                        FMGL(re),
                                      other->
                                        FMGL(re))) &&
        (x10aux::struct_equals(this_->
                                 FMGL(im),
                               other->
                                 FMGL(im)));
        
    }
    static x10::lang::Complex
      x10__lang__Complex____x10__lang__Complex__this(
      x10::lang::Complex this_) {
        
        //#line 19 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Complex.x10": x10.ast.X10Return_c
        return this_;
        
    }
    
};

} } 
#endif // X10_LANG_COMPLEX_H

namespace x10 { namespace lang { 
class Complex;
} } 

#ifndef X10_LANG_COMPLEX_H_NODEPS
#define X10_LANG_COMPLEX_H_NODEPS
#include <x10/lang/Any.h>
#include <x10/lang/Any.h>
#include <x10/lang/Double.h>
#include <x10/lang/Math.h>
#include <x10/lang/Boolean.h>
#include <x10/lang/String.h>
#include <x10/compiler/Native.h>
#include <x10/compiler/NonEscaping.h>
#include <x10/lang/Int.h>
#ifndef X10_LANG_COMPLEX_H_GENERICS
#define X10_LANG_COMPLEX_H_GENERICS
#endif // X10_LANG_COMPLEX_H_GENERICS
#endif // __X10_LANG_COMPLEX_H_NODEPS
