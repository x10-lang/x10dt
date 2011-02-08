#ifndef __X10_LANG_ITERATOR_H
#define __X10_LANG_ITERATOR_H

#include <x10rt.h>


#define X10_LANG_ANY_H_NODEPS
#include <x10/lang/Any.h>
#undef X10_LANG_ANY_H_NODEPS
namespace x10 { namespace lang { 
class Boolean;
} } 
#include <x10/lang/Boolean.struct_h>
namespace x10 { namespace lang { 

template<class FMGL(T)> class Iterator;
template <> class Iterator<void>;
template<class FMGL(T)> class Iterator   {
    public:
    RTT_H_DECLS_INTERFACE
    
    template <class I> struct itable {
        itable(x10_boolean (I::*equals) (x10aux::ref<x10::lang::Any>), x10_boolean (I::*hasNext) (), x10_int (I::*hashCode) (), FMGL(T) (I::*next) (), x10aux::ref<x10::lang::String> (I::*toString) (), x10aux::ref<x10::lang::String> (I::*typeName) ()) : equals(equals), hasNext(hasNext), hashCode(hashCode), next(next), toString(toString), typeName(typeName) {}
        x10_boolean (I::*equals) (x10aux::ref<x10::lang::Any>);
        x10_boolean (I::*hasNext) ();
        x10_int (I::*hashCode) ();
        FMGL(T) (I::*next) ();
        x10aux::ref<x10::lang::String> (I::*toString) ();
        x10aux::ref<x10::lang::String> (I::*typeName) ();
    };
    
    template <class R> static x10_boolean equals(x10aux::ref<R> _recv, x10aux::ref<x10::lang::Any> arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::lang::Iterator<FMGL(T)> >(_refRecv->_getITables())->equals))(arg0);
    }
    template <class R> static x10_boolean equals(R _recv, x10aux::ref<x10::lang::Any> arg0) {
        return R::_METHODS::equals(_recv, arg0);
    }
    template <class R> static x10_boolean hasNext(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::lang::Iterator<FMGL(T)> >(_refRecv->_getITables())->hasNext))();
    }
    template <class R> static x10_boolean hasNext(R _recv) {
        return R::_METHODS::hasNext(_recv);
    }
    template <class R> static x10_int hashCode(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::lang::Iterator<FMGL(T)> >(_refRecv->_getITables())->hashCode))();
    }
    template <class R> static x10_int hashCode(R _recv) {
        return R::_METHODS::hashCode(_recv);
    }
    template <class R> static FMGL(T) next(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::lang::Iterator<FMGL(T)> >(_refRecv->_getITables())->next))();
    }
    template <class R> static FMGL(T) next(R _recv) {
        return R::_METHODS::next(_recv);
    }
    template <class R> static x10aux::ref<x10::lang::String> toString(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::lang::Iterator<FMGL(T)> >(_refRecv->_getITables())->toString))();
    }
    template <class R> static x10aux::ref<x10::lang::String> toString(R _recv) {
        return R::_METHODS::toString(_recv);
    }
    template <class R> static x10aux::ref<x10::lang::String> typeName(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::lang::Iterator<FMGL(T)> >(_refRecv->_getITables())->typeName))();
    }
    template <class R> static x10aux::ref<x10::lang::String> typeName(R _recv) {
        return R::_METHODS::typeName(_recv);
    }
    void _instance_init();
    
    
};
template <> class Iterator<void> {
    public:
    static x10aux::RuntimeType rtt;
    static const x10aux::RuntimeType* getRTT() { return & rtt; }
    
};

} } 
#endif // X10_LANG_ITERATOR_H

namespace x10 { namespace lang { 
template<class FMGL(T)> class Iterator;
} } 

#ifndef X10_LANG_ITERATOR_H_NODEPS
#define X10_LANG_ITERATOR_H_NODEPS
#include <x10/lang/Any.h>
#include <x10/lang/Boolean.h>
#ifndef X10_LANG_ITERATOR_H_GENERICS
#define X10_LANG_ITERATOR_H_GENERICS
#endif // X10_LANG_ITERATOR_H_GENERICS
#ifndef X10_LANG_ITERATOR_H_IMPLEMENTATION
#define X10_LANG_ITERATOR_H_IMPLEMENTATION
#include <x10/lang/Iterator.h>


template<class FMGL(T)> void x10::lang::Iterator<FMGL(T)>::_instance_init() {
    _I_("Doing initialisation for class: x10::lang::Iterator<FMGL(T)>");
    
}

template<class FMGL(T)> x10aux::RuntimeType x10::lang::Iterator<FMGL(T)>::rtt;
template<class FMGL(T)> void x10::lang::Iterator<FMGL(T)>::_initRTT() {
    const x10aux::RuntimeType *canonical = x10aux::getRTT<x10::lang::Iterator<void> >();
    if (rtt.initStageOne(canonical)) return;
    const x10aux::RuntimeType* parents[1] = { x10aux::getRTT<x10::lang::Any>()};
    const x10aux::RuntimeType* params[1] = { x10aux::getRTT<FMGL(T)>()};
    x10aux::RuntimeType::Variance variances[1] = { x10aux::RuntimeType::covariant};
    const char *baseName = "x10.lang.Iterator";
    rtt.initStageTwo(baseName, x10aux::RuntimeType::interface_kind, 1, parents, 1, params, variances);
}
#endif // X10_LANG_ITERATOR_H_IMPLEMENTATION
#endif // __X10_LANG_ITERATOR_H_NODEPS
