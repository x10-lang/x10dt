#ifndef __X10_LANG_ARITHMETIC_H
#define __X10_LANG_ARITHMETIC_H

#include <x10rt.h>


#define X10_LANG_ANY_H_NODEPS
#include <x10/lang/Any.h>
#undef X10_LANG_ANY_H_NODEPS
namespace x10 { namespace lang { 

template<class FMGL(T)> class Arithmetic;
template <> class Arithmetic<void>;
template<class FMGL(T)> class Arithmetic   {
    public:
    RTT_H_DECLS_INTERFACE
    
    template <class I> struct itable {
        itable(x10_boolean (I::*equals) (x10aux::ref<x10::lang::Any>), x10_int (I::*hashCode) (), FMGL(T) (I::*__times) (FMGL(T)), FMGL(T) (I::*_m3____plus) (), FMGL(T) (I::*_m4____plus) (FMGL(T)), FMGL(T) (I::*_m5____minus) (), FMGL(T) (I::*_m6____minus) (FMGL(T)), FMGL(T) (I::*__over) (FMGL(T)), x10aux::ref<x10::lang::String> (I::*toString) (), x10aux::ref<x10::lang::String> (I::*typeName) ()) : equals(equals), hashCode(hashCode), __times(__times), _m3____plus(_m3____plus), _m4____plus(_m4____plus), _m5____minus(_m5____minus), _m6____minus(_m6____minus), __over(__over), toString(toString), typeName(typeName) {}
        x10_boolean (I::*equals) (x10aux::ref<x10::lang::Any>);
        x10_int (I::*hashCode) ();
        FMGL(T) (I::*__times) (FMGL(T));
        FMGL(T) (I::*_m3____plus) ();
        FMGL(T) (I::*_m4____plus) (FMGL(T));
        FMGL(T) (I::*_m5____minus) ();
        FMGL(T) (I::*_m6____minus) (FMGL(T));
        FMGL(T) (I::*__over) (FMGL(T));
        x10aux::ref<x10::lang::String> (I::*toString) ();
        x10aux::ref<x10::lang::String> (I::*typeName) ();
    };
    
    template <class R> static x10_boolean equals(x10aux::ref<R> _recv, x10aux::ref<x10::lang::Any> arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::lang::Arithmetic<FMGL(T)> >(_refRecv->_getITables())->equals))(arg0);
    }
    template <class R> static x10_boolean equals(R _recv, x10aux::ref<x10::lang::Any> arg0) {
        return R::_METHODS::equals(_recv, arg0);
    }
    template <class R> static x10_int hashCode(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::lang::Arithmetic<FMGL(T)> >(_refRecv->_getITables())->hashCode))();
    }
    template <class R> static x10_int hashCode(R _recv) {
        return R::_METHODS::hashCode(_recv);
    }
    template <class R> static FMGL(T) __times(x10aux::ref<R> _recv, FMGL(T) arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::lang::Arithmetic<FMGL(T)> >(_refRecv->_getITables())->__times))(arg0);
    }
    template <class R> static FMGL(T) __times(R _recv, FMGL(T) arg0) {
        return R::_METHODS::__times(_recv, arg0);
    }
    template <class R> static FMGL(T) _m3____plus(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::lang::Arithmetic<FMGL(T)> >(_refRecv->_getITables())->_m3____plus))();
    }
    template <class R> static FMGL(T) _m3____plus(R _recv) {
        return R::_METHODS::__plus(_recv);
    }
    template <class R> static FMGL(T) _m4____plus(x10aux::ref<R> _recv, FMGL(T) arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::lang::Arithmetic<FMGL(T)> >(_refRecv->_getITables())->_m4____plus))(arg0);
    }
    template <class R> static FMGL(T) _m4____plus(R _recv, FMGL(T) arg0) {
        return R::_METHODS::__plus(_recv, arg0);
    }
    template <class R> static FMGL(T) _m5____minus(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::lang::Arithmetic<FMGL(T)> >(_refRecv->_getITables())->_m5____minus))();
    }
    template <class R> static FMGL(T) _m5____minus(R _recv) {
        return R::_METHODS::__minus(_recv);
    }
    template <class R> static FMGL(T) _m6____minus(x10aux::ref<R> _recv, FMGL(T) arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::lang::Arithmetic<FMGL(T)> >(_refRecv->_getITables())->_m6____minus))(arg0);
    }
    template <class R> static FMGL(T) _m6____minus(R _recv, FMGL(T) arg0) {
        return R::_METHODS::__minus(_recv, arg0);
    }
    template <class R> static FMGL(T) __over(x10aux::ref<R> _recv, FMGL(T) arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::lang::Arithmetic<FMGL(T)> >(_refRecv->_getITables())->__over))(arg0);
    }
    template <class R> static FMGL(T) __over(R _recv, FMGL(T) arg0) {
        return R::_METHODS::__over(_recv, arg0);
    }
    template <class R> static x10aux::ref<x10::lang::String> toString(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::lang::Arithmetic<FMGL(T)> >(_refRecv->_getITables())->toString))();
    }
    template <class R> static x10aux::ref<x10::lang::String> toString(R _recv) {
        return R::_METHODS::toString(_recv);
    }
    template <class R> static x10aux::ref<x10::lang::String> typeName(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::lang::Arithmetic<FMGL(T)> >(_refRecv->_getITables())->typeName))();
    }
    template <class R> static x10aux::ref<x10::lang::String> typeName(R _recv) {
        return R::_METHODS::typeName(_recv);
    }
    void _instance_init();
    
    
};
template <> class Arithmetic<void> {
    public:
    static x10aux::RuntimeType rtt;
    static const x10aux::RuntimeType* getRTT() { return & rtt; }
    
};

} } 
#endif // X10_LANG_ARITHMETIC_H

namespace x10 { namespace lang { 
template<class FMGL(T)> class Arithmetic;
} } 

#ifndef X10_LANG_ARITHMETIC_H_NODEPS
#define X10_LANG_ARITHMETIC_H_NODEPS
#include <x10/lang/Any.h>
#ifndef X10_LANG_ARITHMETIC_H_GENERICS
#define X10_LANG_ARITHMETIC_H_GENERICS
#endif // X10_LANG_ARITHMETIC_H_GENERICS
#ifndef X10_LANG_ARITHMETIC_H_IMPLEMENTATION
#define X10_LANG_ARITHMETIC_H_IMPLEMENTATION
#include <x10/lang/Arithmetic.h>


template<class FMGL(T)> void x10::lang::Arithmetic<FMGL(T)>::_instance_init() {
    _I_("Doing initialisation for class: x10::lang::Arithmetic<FMGL(T)>");
    
}

template<class FMGL(T)> x10aux::RuntimeType x10::lang::Arithmetic<FMGL(T)>::rtt;
template<class FMGL(T)> void x10::lang::Arithmetic<FMGL(T)>::_initRTT() {
    const x10aux::RuntimeType *canonical = x10aux::getRTT<x10::lang::Arithmetic<void> >();
    if (rtt.initStageOne(canonical)) return;
    const x10aux::RuntimeType* parents[1] = { x10aux::getRTT<x10::lang::Any>()};
    const x10aux::RuntimeType* params[1] = { x10aux::getRTT<FMGL(T)>()};
    x10aux::RuntimeType::Variance variances[1] = { x10aux::RuntimeType::invariant};
    const char *baseName = "x10.lang.Arithmetic";
    rtt.initStageTwo(baseName, x10aux::RuntimeType::interface_kind, 1, parents, 1, params, variances);
}
#endif // X10_LANG_ARITHMETIC_H_IMPLEMENTATION
#endif // __X10_LANG_ARITHMETIC_H_NODEPS
