#ifndef __X10_LANG_INDEXABLE_H
#define __X10_LANG_INDEXABLE_H

#include <x10rt.h>


#define X10_LANG_ANY_H_NODEPS
#include <x10/lang/Any.h>
#undef X10_LANG_ANY_H_NODEPS
#define X10_LANG_FUN_0_1_H_NODEPS
#include <x10/lang/Fun_0_1.h>
#undef X10_LANG_FUN_0_1_H_NODEPS
namespace x10 { namespace lang { 

template<class FMGL(I), class FMGL(V)> class Indexable;
template <> class Indexable<void, void>;
template<class FMGL(I), class FMGL(V)> class Indexable   {
    public:
    RTT_H_DECLS_INTERFACE
    
    template <class I> struct itable {
        itable(x10_boolean (I::*equals) (x10aux::ref<x10::lang::Any>), x10_int (I::*hashCode) (), FMGL(V) (I::*__apply) (FMGL(I)), x10aux::ref<x10::lang::String> (I::*toString) (), x10aux::ref<x10::lang::String> (I::*typeName) ()) : equals(equals), hashCode(hashCode), __apply(__apply), toString(toString), typeName(typeName) {}
        x10_boolean (I::*equals) (x10aux::ref<x10::lang::Any>);
        x10_int (I::*hashCode) ();
        FMGL(V) (I::*__apply) (FMGL(I));
        x10aux::ref<x10::lang::String> (I::*toString) ();
        x10aux::ref<x10::lang::String> (I::*typeName) ();
    };
    
    template <class R> static x10_boolean equals(x10aux::ref<R> _recv, x10aux::ref<x10::lang::Any> arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::lang::Indexable<FMGL(I), FMGL(V)> >(_refRecv->_getITables())->equals))(arg0);
    }
    template <class R> static x10_boolean equals(R _recv, x10aux::ref<x10::lang::Any> arg0) {
        return R::_METHODS::equals(_recv, arg0);
    }
    template <class R> static x10_int hashCode(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::lang::Indexable<FMGL(I), FMGL(V)> >(_refRecv->_getITables())->hashCode))();
    }
    template <class R> static x10_int hashCode(R _recv) {
        return R::_METHODS::hashCode(_recv);
    }
    template <class R> static FMGL(V) __apply(x10aux::ref<R> _recv, FMGL(I) arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::lang::Indexable<FMGL(I), FMGL(V)> >(_refRecv->_getITables())->__apply))(arg0);
    }
    template <class R> static FMGL(V) __apply(R _recv, FMGL(I) arg0) {
        return R::_METHODS::__apply(_recv, arg0);
    }
    template <class R> static x10aux::ref<x10::lang::String> toString(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::lang::Indexable<FMGL(I), FMGL(V)> >(_refRecv->_getITables())->toString))();
    }
    template <class R> static x10aux::ref<x10::lang::String> toString(R _recv) {
        return R::_METHODS::toString(_recv);
    }
    template <class R> static x10aux::ref<x10::lang::String> typeName(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::lang::Indexable<FMGL(I), FMGL(V)> >(_refRecv->_getITables())->typeName))();
    }
    template <class R> static x10aux::ref<x10::lang::String> typeName(R _recv) {
        return R::_METHODS::typeName(_recv);
    }
    
};
template <> class Indexable<void, void> {
    public:
    static x10aux::RuntimeType rtt;
    static const x10aux::RuntimeType* getRTT() { return & rtt; }
    
};

} } 
#endif // X10_LANG_INDEXABLE_H

namespace x10 { namespace lang { 
template<class FMGL(I), class FMGL(V)> class Indexable;
} } 

#ifndef X10_LANG_INDEXABLE_H_NODEPS
#define X10_LANG_INDEXABLE_H_NODEPS
#include <x10/lang/Any.h>
#include <x10/lang/Fun_0_1.h>
#ifndef X10_LANG_INDEXABLE_H_GENERICS
#define X10_LANG_INDEXABLE_H_GENERICS
#endif // X10_LANG_INDEXABLE_H_GENERICS
#ifndef X10_LANG_INDEXABLE_H_IMPLEMENTATION
#define X10_LANG_INDEXABLE_H_IMPLEMENTATION
#include <x10/lang/Indexable.h>


template<class FMGL(I), class FMGL(V)> x10aux::RuntimeType x10::lang::Indexable<FMGL(I), FMGL(V)>::rtt;
template<class FMGL(I), class FMGL(V)> void x10::lang::Indexable<FMGL(I), FMGL(V)>::_initRTT() {
    const x10aux::RuntimeType *canonical = x10aux::getRTT<x10::lang::Indexable<void, void> >();
    if (rtt.initStageOne(canonical)) return;
    const x10aux::RuntimeType* parents[2] = { x10aux::getRTT<x10::lang::Any>(), x10aux::getRTT<x10::lang::Fun_0_1<FMGL(I), FMGL(V)> >()};
    const x10aux::RuntimeType* params[2] = { x10aux::getRTT<FMGL(I)>(), x10aux::getRTT<FMGL(V)>()};
    x10aux::RuntimeType::Variance variances[2] = { x10aux::RuntimeType::contravariant, x10aux::RuntimeType::covariant};
    const char *baseName = "x10.lang.Indexable";
    rtt.initStageTwo(baseName, x10aux::RuntimeType::interface_kind, 2, parents, 2, params, variances);
}
#endif // X10_LANG_INDEXABLE_H_IMPLEMENTATION
#endif // __X10_LANG_INDEXABLE_H_NODEPS
