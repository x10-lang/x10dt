#ifndef __X10_UTIL_PAIR_H
#define __X10_UTIL_PAIR_H

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
#include <x10/util/Pair.struct_h>

namespace x10 { namespace util { 

template<class FMGL(T), class FMGL(U)> class Pair_methods  {
    public:
    static void _instance_init(x10::util::Pair<FMGL(T), FMGL(U)>& this_);
    
    static void _constructor(x10::util::Pair<FMGL(T), FMGL(U)>& this_, FMGL(T) first,
                             FMGL(U) second);
    
    inline static x10::util::Pair<FMGL(T), FMGL(U)> _make(FMGL(T) first,
                                                          FMGL(U) second)
    {
        x10::util::Pair<FMGL(T), FMGL(U)> this_; 
        _constructor(this_, first,
        second);
        return this_;
    }
    
    static x10aux::ref<x10::lang::String>
      toString(
      x10::util::Pair<FMGL(T), FMGL(U)> this_);
    static x10_int
      hashCode(
      x10::util::Pair<FMGL(T), FMGL(U)> this_);
    static x10_boolean
      equals(
      x10::util::Pair<FMGL(T), FMGL(U)> this_, x10aux::ref<x10::lang::Any> other);
    static x10_boolean
      equals(
      x10::util::Pair<FMGL(T), FMGL(U)> this_, x10::util::Pair<FMGL(T), FMGL(U)> other);
    static x10_boolean
      _struct_equals(
      x10::util::Pair<FMGL(T), FMGL(U)> this_, x10aux::ref<x10::lang::Any> other);
    static x10_boolean
      _struct_equals(
      x10::util::Pair<FMGL(T), FMGL(U)> this_, x10::util::Pair<FMGL(T), FMGL(U)> other);
    static x10::util::Pair<FMGL(T), FMGL(U)>
      x10__util__Pair____x10__util__Pair__this(
      x10::util::Pair<FMGL(T), FMGL(U)> this_);
    
};
template <> class Pair_methods<void, void> {
    public:
    
};

} } 
#endif // X10_UTIL_PAIR_H

namespace x10 { namespace util { 
template<class FMGL(T), class FMGL(U)> class Pair;
} } 

#ifndef X10_UTIL_PAIR_H_NODEPS
#define X10_UTIL_PAIR_H_NODEPS
#include <x10/lang/Any.h>
#include <x10/lang/Any.h>
#include <x10/lang/String.h>
#include <x10/compiler/Native.h>
#include <x10/compiler/NonEscaping.h>
#include <x10/lang/Int.h>
#include <x10/lang/Boolean.h>
#ifndef X10_UTIL_PAIR_H_GENERICS
#define X10_UTIL_PAIR_H_GENERICS
#endif // X10_UTIL_PAIR_H_GENERICS
#ifndef X10_UTIL_PAIR_H_IMPLEMENTATION
#define X10_UTIL_PAIR_H_IMPLEMENTATION
#include <x10/util/Pair.h>


namespace x10 { namespace util { 
template<class FMGL(T), class FMGL(U)> class Pair_ithunk0 : public x10::util::Pair<FMGL(T), FMGL(U)> {
public:
    static x10::lang::Any::itable<Pair_ithunk0<FMGL(T), FMGL(U)> > itable;
    x10_boolean equals(x10aux::ref<x10::lang::Any> arg0) {
        return x10::util::Pair_methods<FMGL(T), FMGL(U)>::equals(*this, arg0);
    }
    x10_int hashCode() {
        return x10::util::Pair_methods<FMGL(T), FMGL(U)>::hashCode(*this);
    }
    x10aux::ref<x10::lang::String> toString() {
        return x10::util::Pair_methods<FMGL(T), FMGL(U)>::toString(*this);
    }
    x10aux::ref<x10::lang::String> typeName() {
        return x10aux::type_name(*this);
    }
    
};
template<class FMGL(T), class FMGL(U)> x10::lang::Any::itable<Pair_ithunk0<FMGL(T), FMGL(U)> >  Pair_ithunk0<FMGL(T), FMGL(U)>::itable(&Pair_ithunk0<FMGL(T), FMGL(U)>::equals, &Pair_ithunk0<FMGL(T), FMGL(U)>::hashCode, &Pair_ithunk0<FMGL(T), FMGL(U)>::toString, &Pair_ithunk0<FMGL(T), FMGL(U)>::typeName);
template<class FMGL(T), class FMGL(U)> class Pair_iboxithunk0 : public x10::lang::IBox<x10::util::Pair<FMGL(T), FMGL(U)> > {
public:
    static x10::lang::Any::itable<Pair_iboxithunk0<FMGL(T), FMGL(U)> > itable;
    x10_boolean equals(x10aux::ref<x10::lang::Any> arg0) {
        return x10::util::Pair_methods<FMGL(T), FMGL(U)>::equals(this->value, arg0);
    }
    x10_int hashCode() {
        return x10::util::Pair_methods<FMGL(T), FMGL(U)>::hashCode(this->value);
    }
    x10aux::ref<x10::lang::String> toString() {
        return x10::util::Pair_methods<FMGL(T), FMGL(U)>::toString(this->value);
    }
    x10aux::ref<x10::lang::String> typeName() {
        return x10aux::type_name(this->value);
    }
    
};
template<class FMGL(T), class FMGL(U)> x10::lang::Any::itable<Pair_iboxithunk0<FMGL(T), FMGL(U)> >  Pair_iboxithunk0<FMGL(T), FMGL(U)>::itable(&Pair_iboxithunk0<FMGL(T), FMGL(U)>::equals, &Pair_iboxithunk0<FMGL(T), FMGL(U)>::hashCode, &Pair_iboxithunk0<FMGL(T), FMGL(U)>::toString, &Pair_iboxithunk0<FMGL(T), FMGL(U)>::typeName);
} } 
template<class FMGL(T), class FMGL(U)> x10aux::itable_entry x10::util::Pair<FMGL(T), FMGL(U)>::_itables[2] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::Any>, &Pair_ithunk0<FMGL(T), FMGL(U)>::itable), x10aux::itable_entry(NULL, (void*)x10aux::getRTT<x10::util::Pair<FMGL(T), FMGL(U)> >())};
template<class FMGL(T), class FMGL(U)> x10aux::itable_entry x10::util::Pair<FMGL(T), FMGL(U)>::_iboxitables[2] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::Any>, &Pair_iboxithunk0<FMGL(T), FMGL(U)>::itable), x10aux::itable_entry(NULL, (void*)x10aux::getRTT<x10::util::Pair<FMGL(T), FMGL(U)> >())};
template<class FMGL(T), class FMGL(U)> void x10::util::Pair_methods<FMGL(T), FMGL(U)>::_instance_init(x10::util::Pair<FMGL(T), FMGL(U)>& this_) {
    _I_("Doing initialisation for class: x10::util::Pair<FMGL(T), FMGL(U)>");
    
}


//#line 18 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Pair.x10": x10.ast.X10FieldDecl_c

//#line 19 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Pair.x10": x10.ast.X10FieldDecl_c

//#line 21 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Pair.x10": x10.ast.X10ConstructorDecl_c
template<class FMGL(T), class FMGL(U)> void x10::util::Pair_methods<FMGL(T), FMGL(U)>::_constructor(
                                         x10::util::Pair<FMGL(T), FMGL(U)>& this_, FMGL(T) first,
                                         FMGL(U) second) {
    {
     
    }
    
    //#line 22 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Pair.x10": polyglot.ast.Eval_c
    this_->FMGL(first) = first;
    
    //#line 23 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Pair.x10": polyglot.ast.Eval_c
    this_->FMGL(second) = second;
    
}


//#line 26 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Pair.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T), class FMGL(U)> x10aux::ref<x10::lang::String>
  x10::util::Pair_methods<FMGL(T), FMGL(U)>::toString(
  x10::util::Pair<FMGL(T), FMGL(U)> this_) {
    
    //#line 27 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Pair.x10": x10.ast.X10Return_c
    return ((((((((x10aux::string_utils::lit("(")) + (this_->
                                                        FMGL(first)))) + (x10aux::string_utils::lit(", ")))) + (this_->
                                                                                                                  FMGL(second)))) + (x10aux::string_utils::lit(")")));
    
}

//#line 17 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Pair.x10": x10.ast.X10MethodDecl_c

//#line 17 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Pair.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T), class FMGL(U)> x10_int x10::util::Pair_methods<FMGL(T), FMGL(U)>::hashCode(
  x10::util::Pair<FMGL(T), FMGL(U)> this_) {
    
    //#line 17 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Pair.x10": x10.ast.X10LocalDecl_c
    x10_int result = ((x10_int)1);
    
    //#line 17 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Pair.x10": polyglot.ast.Eval_c
    result = ((x10_int) ((((x10_int) ((((x10_int)8191)) * (result)))) + (x10aux::hash_code(this_->
                                                                                             FMGL(first)))));
    
    //#line 17 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Pair.x10": polyglot.ast.Eval_c
    result = ((x10_int) ((((x10_int) ((((x10_int)8191)) * (result)))) + (x10aux::hash_code(this_->
                                                                                             FMGL(second)))));
    
    //#line 17 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Pair.x10": x10.ast.X10Return_c
    return result;
    
}

//#line 17 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Pair.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T), class FMGL(U)> x10_boolean
  x10::util::Pair_methods<FMGL(T), FMGL(U)>::equals(
  x10::util::Pair<FMGL(T), FMGL(U)> this_, x10aux::ref<x10::lang::Any> other) {
    
    //#line 17 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Pair.x10": x10.ast.X10If_c
    if (!(x10aux::instanceof<x10::util::Pair<FMGL(T), FMGL(U)> >(other)))
    {
        
        //#line 17 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Pair.x10": x10.ast.X10Return_c
        return false;
        
    }
    
    //#line 17 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Pair.x10": x10.ast.X10Return_c
    return x10::util::Pair_methods<FMGL(T), FMGL(U)>::equals(this_, 
             x10aux::class_cast<x10::util::Pair<FMGL(T), FMGL(U)> >(other));
    
}

//#line 17 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Pair.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T), class FMGL(U)> x10_boolean
  x10::util::Pair_methods<FMGL(T), FMGL(U)>::equals(
  x10::util::Pair<FMGL(T), FMGL(U)> this_, x10::util::Pair<FMGL(T), FMGL(U)> other) {
    
    //#line 17 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Pair.x10": x10.ast.X10Return_c
    return (x10aux::struct_equals(this_->
                                    FMGL(first),
                                  other->
                                    FMGL(first))) &&
    (x10aux::struct_equals(this_->
                             FMGL(second),
                           other->
                             FMGL(second)));
    
}

//#line 17 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Pair.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T), class FMGL(U)> x10_boolean
  x10::util::Pair_methods<FMGL(T), FMGL(U)>::_struct_equals(
  x10::util::Pair<FMGL(T), FMGL(U)> this_, x10aux::ref<x10::lang::Any> other) {
    
    //#line 17 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Pair.x10": x10.ast.X10If_c
    if (!(x10aux::instanceof<x10::util::Pair<FMGL(T), FMGL(U)> >(other)))
    {
        
        //#line 17 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Pair.x10": x10.ast.X10Return_c
        return false;
        
    }
    
    //#line 17 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Pair.x10": x10.ast.X10Return_c
    return x10::util::Pair_methods<FMGL(T), FMGL(U)>::_struct_equals(this_, 
             x10aux::class_cast<x10::util::Pair<FMGL(T), FMGL(U)> >(other));
    
}

//#line 17 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Pair.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T), class FMGL(U)> x10_boolean
  x10::util::Pair_methods<FMGL(T), FMGL(U)>::_struct_equals(
  x10::util::Pair<FMGL(T), FMGL(U)> this_, x10::util::Pair<FMGL(T), FMGL(U)> other) {
    
    //#line 17 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Pair.x10": x10.ast.X10Return_c
    return (x10aux::struct_equals(this_->
                                    FMGL(first),
                                  other->
                                    FMGL(first))) &&
    (x10aux::struct_equals(this_->
                             FMGL(second),
                           other->
                             FMGL(second)));
    
}

//#line 17 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Pair.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T), class FMGL(U)> x10::util::Pair<FMGL(T), FMGL(U)>
  x10::util::Pair_methods<FMGL(T), FMGL(U)>::x10__util__Pair____x10__util__Pair__this(
  x10::util::Pair<FMGL(T), FMGL(U)> this_) {
    
    //#line 17 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Pair.x10": x10.ast.X10Return_c
    return this_;
    
}
template<class FMGL(T), class FMGL(U)> void x10::util::Pair<FMGL(T), FMGL(U)>::_serialize(x10::util::Pair<FMGL(T), FMGL(U)> this_, x10aux::serialization_buffer& buf) {
    buf.write(this_->FMGL(first));
    buf.write(this_->FMGL(second));
    
}

template<class FMGL(T), class FMGL(U)> void x10::util::Pair<FMGL(T), FMGL(U)>::_deserialize_body(x10aux::deserialization_buffer& buf) {
    FMGL(first) = buf.read<FMGL(T)>();
    FMGL(second) = buf.read<FMGL(U)>();
}


template<class FMGL(T), class FMGL(U)> x10_boolean x10::util::Pair<FMGL(T), FMGL(U)>::equals(x10aux::ref<x10::lang::Any> that) {
    return x10::util::Pair_methods<FMGL(T), FMGL(U)>::equals(*this, that);
}
template<class FMGL(T), class FMGL(U)> x10_boolean x10::util::Pair<FMGL(T), FMGL(U)>::equals(x10::util::Pair<FMGL(T), FMGL(U)> that) {
    return x10::util::Pair_methods<FMGL(T), FMGL(U)>::equals(*this, that);
}
template<class FMGL(T), class FMGL(U)> x10_boolean x10::util::Pair<FMGL(T), FMGL(U)>::_struct_equals(x10aux::ref<x10::lang::Any> that) {
    return x10::util::Pair_methods<FMGL(T), FMGL(U)>::_struct_equals(*this, that);
}
template<class FMGL(T), class FMGL(U)> x10_boolean x10::util::Pair<FMGL(T), FMGL(U)>::_struct_equals(x10::util::Pair<FMGL(T), FMGL(U)> that) {
    return x10::util::Pair_methods<FMGL(T), FMGL(U)>::_struct_equals(*this, that);
}
template<class FMGL(T), class FMGL(U)> x10aux::ref<x10::lang::String> x10::util::Pair<FMGL(T), FMGL(U)>::toString() {
    return x10::util::Pair_methods<FMGL(T), FMGL(U)>::toString(*this);
}
template<class FMGL(T), class FMGL(U)> x10_int x10::util::Pair<FMGL(T), FMGL(U)>::hashCode() {
    return x10::util::Pair_methods<FMGL(T), FMGL(U)>::hashCode(*this);
}
template<class FMGL(T), class FMGL(U)> x10aux::RuntimeType x10::util::Pair<FMGL(T), FMGL(U)>::rtt;
template<class FMGL(T), class FMGL(U)> void x10::util::Pair<FMGL(T), FMGL(U)>::_initRTT() {
    const x10aux::RuntimeType *canonical = x10aux::getRTT<x10::util::Pair<void, void> >();
    if (rtt.initStageOne(canonical)) return;
    const x10aux::RuntimeType* parents[2] = { x10aux::getRTT<x10::lang::Any>(), x10aux::getRTT<x10::lang::Any>()};
    const x10aux::RuntimeType* params[2] = { x10aux::getRTT<FMGL(T)>(), x10aux::getRTT<FMGL(U)>()};
    x10aux::RuntimeType::Variance variances[2] = { x10aux::RuntimeType::invariant, x10aux::RuntimeType::invariant};
    const char *baseName = "x10.util.Pair";
    rtt.initStageTwo(baseName, x10aux::RuntimeType::struct_kind, 2, parents, 2, params, variances);
}
#endif // X10_UTIL_PAIR_H_IMPLEMENTATION
#endif // __X10_UTIL_PAIR_H_NODEPS
