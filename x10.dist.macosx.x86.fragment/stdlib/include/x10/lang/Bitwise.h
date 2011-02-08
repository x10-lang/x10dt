#ifndef __X10_LANG_BITWISE_H
#define __X10_LANG_BITWISE_H

#include <x10rt.h>


#define X10_LANG_ANY_H_NODEPS
#include <x10/lang/Any.h>
#undef X10_LANG_ANY_H_NODEPS
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace lang { 

template<class FMGL(T)> class Bitwise;
template <> class Bitwise<void>;
template<class FMGL(T)> class Bitwise   {
    public:
    RTT_H_DECLS_INTERFACE
    
    template <class I> struct itable {
        itable(x10_boolean (I::*equals) (x10aux::ref<x10::lang::Any>), x10_int (I::*hashCode) (), FMGL(T) (I::*__ampersand) (FMGL(T)), FMGL(T) (I::*__left) (x10_int), FMGL(T) (I::*__right) (x10_int), FMGL(T) (I::*__unsigned_right) (x10_int), FMGL(T) (I::*__caret) (FMGL(T)), FMGL(T) (I::*__bar) (FMGL(T)), FMGL(T) (I::*__tilde) (), x10aux::ref<x10::lang::String> (I::*toString) (), x10aux::ref<x10::lang::String> (I::*typeName) ()) : equals(equals), hashCode(hashCode), __ampersand(__ampersand), __left(__left), __right(__right), __unsigned_right(__unsigned_right), __caret(__caret), __bar(__bar), __tilde(__tilde), toString(toString), typeName(typeName) {}
        x10_boolean (I::*equals) (x10aux::ref<x10::lang::Any>);
        x10_int (I::*hashCode) ();
        FMGL(T) (I::*__ampersand) (FMGL(T));
        FMGL(T) (I::*__left) (x10_int);
        FMGL(T) (I::*__right) (x10_int);
        FMGL(T) (I::*__unsigned_right) (x10_int);
        FMGL(T) (I::*__caret) (FMGL(T));
        FMGL(T) (I::*__bar) (FMGL(T));
        FMGL(T) (I::*__tilde) ();
        x10aux::ref<x10::lang::String> (I::*toString) ();
        x10aux::ref<x10::lang::String> (I::*typeName) ();
    };
    
    template <class R> static x10_boolean equals(x10aux::ref<R> _recv, x10aux::ref<x10::lang::Any> arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::lang::Bitwise<FMGL(T)> >(_refRecv->_getITables())->equals))(arg0);
    }
    template <class R> static x10_boolean equals(R _recv, x10aux::ref<x10::lang::Any> arg0) {
        return R::_METHODS::equals(_recv, arg0);
    }
    template <class R> static x10_int hashCode(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::lang::Bitwise<FMGL(T)> >(_refRecv->_getITables())->hashCode))();
    }
    template <class R> static x10_int hashCode(R _recv) {
        return R::_METHODS::hashCode(_recv);
    }
    template <class R> static FMGL(T) __ampersand(x10aux::ref<R> _recv, FMGL(T) arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::lang::Bitwise<FMGL(T)> >(_refRecv->_getITables())->__ampersand))(arg0);
    }
    template <class R> static FMGL(T) __ampersand(R _recv, FMGL(T) arg0) {
        return R::_METHODS::__ampersand(_recv, arg0);
    }
    template <class R> static FMGL(T) __left(x10aux::ref<R> _recv, x10_int arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::lang::Bitwise<FMGL(T)> >(_refRecv->_getITables())->__left))(arg0);
    }
    template <class R> static FMGL(T) __left(R _recv, x10_int arg0) {
        return R::_METHODS::__left(_recv, arg0);
    }
    template <class R> static FMGL(T) __right(x10aux::ref<R> _recv, x10_int arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::lang::Bitwise<FMGL(T)> >(_refRecv->_getITables())->__right))(arg0);
    }
    template <class R> static FMGL(T) __right(R _recv, x10_int arg0) {
        return R::_METHODS::__right(_recv, arg0);
    }
    template <class R> static FMGL(T) __unsigned_right(x10aux::ref<R> _recv, x10_int arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::lang::Bitwise<FMGL(T)> >(_refRecv->_getITables())->__unsigned_right))(arg0);
    }
    template <class R> static FMGL(T) __unsigned_right(R _recv, x10_int arg0) {
        return R::_METHODS::__unsigned_right(_recv, arg0);
    }
    template <class R> static FMGL(T) __caret(x10aux::ref<R> _recv, FMGL(T) arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::lang::Bitwise<FMGL(T)> >(_refRecv->_getITables())->__caret))(arg0);
    }
    template <class R> static FMGL(T) __caret(R _recv, FMGL(T) arg0) {
        return R::_METHODS::__caret(_recv, arg0);
    }
    template <class R> static FMGL(T) __bar(x10aux::ref<R> _recv, FMGL(T) arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::lang::Bitwise<FMGL(T)> >(_refRecv->_getITables())->__bar))(arg0);
    }
    template <class R> static FMGL(T) __bar(R _recv, FMGL(T) arg0) {
        return R::_METHODS::__bar(_recv, arg0);
    }
    template <class R> static FMGL(T) __tilde(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::lang::Bitwise<FMGL(T)> >(_refRecv->_getITables())->__tilde))();
    }
    template <class R> static FMGL(T) __tilde(R _recv) {
        return R::_METHODS::__tilde(_recv);
    }
    template <class R> static x10aux::ref<x10::lang::String> toString(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::lang::Bitwise<FMGL(T)> >(_refRecv->_getITables())->toString))();
    }
    template <class R> static x10aux::ref<x10::lang::String> toString(R _recv) {
        return R::_METHODS::toString(_recv);
    }
    template <class R> static x10aux::ref<x10::lang::String> typeName(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::lang::Bitwise<FMGL(T)> >(_refRecv->_getITables())->typeName))();
    }
    template <class R> static x10aux::ref<x10::lang::String> typeName(R _recv) {
        return R::_METHODS::typeName(_recv);
    }
    void _instance_init();
    
    
};
template <> class Bitwise<void> {
    public:
    static x10aux::RuntimeType rtt;
    static const x10aux::RuntimeType* getRTT() { return & rtt; }
    
};

} } 
#endif // X10_LANG_BITWISE_H

namespace x10 { namespace lang { 
template<class FMGL(T)> class Bitwise;
} } 

#ifndef X10_LANG_BITWISE_H_NODEPS
#define X10_LANG_BITWISE_H_NODEPS
#include <x10/lang/Any.h>
#include <x10/lang/Int.h>
#ifndef X10_LANG_BITWISE_H_GENERICS
#define X10_LANG_BITWISE_H_GENERICS
#endif // X10_LANG_BITWISE_H_GENERICS
#ifndef X10_LANG_BITWISE_H_IMPLEMENTATION
#define X10_LANG_BITWISE_H_IMPLEMENTATION
#include <x10/lang/Bitwise.h>


template<class FMGL(T)> void x10::lang::Bitwise<FMGL(T)>::_instance_init() {
    _I_("Doing initialisation for class: x10::lang::Bitwise<FMGL(T)>");
    
}

template<class FMGL(T)> x10aux::RuntimeType x10::lang::Bitwise<FMGL(T)>::rtt;
template<class FMGL(T)> void x10::lang::Bitwise<FMGL(T)>::_initRTT() {
    const x10aux::RuntimeType *canonical = x10aux::getRTT<x10::lang::Bitwise<void> >();
    if (rtt.initStageOne(canonical)) return;
    const x10aux::RuntimeType* parents[1] = { x10aux::getRTT<x10::lang::Any>()};
    const x10aux::RuntimeType* params[1] = { x10aux::getRTT<FMGL(T)>()};
    x10aux::RuntimeType::Variance variances[1] = { x10aux::RuntimeType::invariant};
    const char *baseName = "x10.lang.Bitwise";
    rtt.initStageTwo(baseName, x10aux::RuntimeType::interface_kind, 1, parents, 1, params, variances);
}
#endif // X10_LANG_BITWISE_H_IMPLEMENTATION
#endif // __X10_LANG_BITWISE_H_NODEPS
