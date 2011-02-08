#ifndef __X10_LANG_REDUCIBLE_H
#define __X10_LANG_REDUCIBLE_H

#include <x10rt.h>


#define X10_LANG_ANY_H_NODEPS
#include <x10/lang/Any.h>
#undef X10_LANG_ANY_H_NODEPS
namespace x10 { namespace lang { 

template<class FMGL(T)> class Reducible;
template <> class Reducible<void>;
template<class FMGL(T)> class Reducible   {
    public:
    RTT_H_DECLS_INTERFACE
    
    template <class I> struct itable {
        itable(x10_boolean (I::*equals) (x10aux::ref<x10::lang::Any>), x10_int (I::*hashCode) (), FMGL(T) (I::*__apply) (FMGL(T), FMGL(T)), x10aux::ref<x10::lang::String> (I::*toString) (), x10aux::ref<x10::lang::String> (I::*typeName) (), FMGL(T) (I::*zero) ()) : equals(equals), hashCode(hashCode), __apply(__apply), toString(toString), typeName(typeName), zero(zero) {}
        x10_boolean (I::*equals) (x10aux::ref<x10::lang::Any>);
        x10_int (I::*hashCode) ();
        FMGL(T) (I::*__apply) (FMGL(T), FMGL(T));
        x10aux::ref<x10::lang::String> (I::*toString) ();
        x10aux::ref<x10::lang::String> (I::*typeName) ();
        FMGL(T) (I::*zero) ();
    };
    
    template <class R> static x10_boolean equals(x10aux::ref<R> _recv, x10aux::ref<x10::lang::Any> arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::lang::Reducible<FMGL(T)> >(_refRecv->_getITables())->equals))(arg0);
    }
    template <class R> static x10_boolean equals(R _recv, x10aux::ref<x10::lang::Any> arg0) {
        return R::_METHODS::equals(_recv, arg0);
    }
    template <class R> static x10_int hashCode(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::lang::Reducible<FMGL(T)> >(_refRecv->_getITables())->hashCode))();
    }
    template <class R> static x10_int hashCode(R _recv) {
        return R::_METHODS::hashCode(_recv);
    }
    template <class R> static FMGL(T) __apply(x10aux::ref<R> _recv, FMGL(T) arg0, FMGL(T) arg1) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::lang::Reducible<FMGL(T)> >(_refRecv->_getITables())->__apply))(arg0, arg1);
    }
    template <class R> static FMGL(T) __apply(R _recv, FMGL(T) arg0, FMGL(T) arg1) {
        return R::_METHODS::__apply(_recv, arg0, arg1);
    }
    template <class R> static x10aux::ref<x10::lang::String> toString(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::lang::Reducible<FMGL(T)> >(_refRecv->_getITables())->toString))();
    }
    template <class R> static x10aux::ref<x10::lang::String> toString(R _recv) {
        return R::_METHODS::toString(_recv);
    }
    template <class R> static x10aux::ref<x10::lang::String> typeName(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::lang::Reducible<FMGL(T)> >(_refRecv->_getITables())->typeName))();
    }
    template <class R> static x10aux::ref<x10::lang::String> typeName(R _recv) {
        return R::_METHODS::typeName(_recv);
    }
    template <class R> static FMGL(T) zero(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::lang::Reducible<FMGL(T)> >(_refRecv->_getITables())->zero))();
    }
    template <class R> static FMGL(T) zero(R _recv) {
        return R::_METHODS::zero(_recv);
    }
    void _instance_init();
    
    
};
template <> class Reducible<void> {
    public:
    static x10aux::RuntimeType rtt;
    static const x10aux::RuntimeType* getRTT() { return & rtt; }
    
};

} } 
#endif // X10_LANG_REDUCIBLE_H

namespace x10 { namespace lang { 
template<class FMGL(T)> class Reducible;
} } 

#ifndef X10_LANG_REDUCIBLE_H_NODEPS
#define X10_LANG_REDUCIBLE_H_NODEPS
#include <x10/lang/Any.h>
#ifndef X10_LANG_REDUCIBLE_H_GENERICS
#define X10_LANG_REDUCIBLE_H_GENERICS
#endif // X10_LANG_REDUCIBLE_H_GENERICS
#ifndef X10_LANG_REDUCIBLE_H_IMPLEMENTATION
#define X10_LANG_REDUCIBLE_H_IMPLEMENTATION
#include <x10/lang/Reducible.h>


template<class FMGL(T)> void x10::lang::Reducible<FMGL(T)>::_instance_init() {
    _I_("Doing initialisation for class: x10::lang::Reducible<FMGL(T)>");
    
}

template<class FMGL(T)> x10aux::RuntimeType x10::lang::Reducible<FMGL(T)>::rtt;
template<class FMGL(T)> void x10::lang::Reducible<FMGL(T)>::_initRTT() {
    const x10aux::RuntimeType *canonical = x10aux::getRTT<x10::lang::Reducible<void> >();
    if (rtt.initStageOne(canonical)) return;
    const x10aux::RuntimeType* parents[1] = { x10aux::getRTT<x10::lang::Any>()};
    const x10aux::RuntimeType* params[1] = { x10aux::getRTT<FMGL(T)>()};
    x10aux::RuntimeType::Variance variances[1] = { x10aux::RuntimeType::invariant};
    const char *baseName = "x10.lang.Reducible";
    rtt.initStageTwo(baseName, x10aux::RuntimeType::interface_kind, 1, parents, 1, params, variances);
}
#endif // X10_LANG_REDUCIBLE_H_IMPLEMENTATION
#endif // __X10_LANG_REDUCIBLE_H_NODEPS
