#ifndef __X10_LANG_SEQUENCE_H
#define __X10_LANG_SEQUENCE_H

#include <x10rt.h>


#define X10_LANG_ANY_H_NODEPS
#include <x10/lang/Any.h>
#undef X10_LANG_ANY_H_NODEPS
#define X10_LANG_FUN_0_1_H_NODEPS
#include <x10/lang/Fun_0_1.h>
#undef X10_LANG_FUN_0_1_H_NODEPS
#define X10_LANG_ITERABLE_H_NODEPS
#include <x10/lang/Iterable.h>
#undef X10_LANG_ITERABLE_H_NODEPS
#define X10_LANG_INT_STRUCT_H_NODEPS
#include <x10/lang/Int.struct_h>
#undef X10_LANG_INT_STRUCT_H_NODEPS
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace compiler { 
class Native;
} } 
namespace x10 { namespace lang { 

template<class FMGL(T)> class Sequence;
template <> class Sequence<void>;
template<class FMGL(T)> class Sequence   {
    public:
    RTT_H_DECLS_INTERFACE
    
    x10_int FMGL(size);
    
    template <class I> struct itable {
        itable(x10_boolean (I::*equals) (x10aux::ref<x10::lang::Any>), x10_int (I::*hashCode) (), x10aux::ref<x10::lang::Iterator<FMGL(T)> > (I::*iterator) (), FMGL(T) (I::*__apply) (x10_int), x10_int (I::*size) (), x10aux::ref<x10::lang::String> (I::*toString) (), x10aux::ref<x10::lang::String> (I::*typeName) ()) : equals(equals), hashCode(hashCode), iterator(iterator), __apply(__apply), size(size), toString(toString), typeName(typeName) {}
        x10_boolean (I::*equals) (x10aux::ref<x10::lang::Any>);
        x10_int (I::*hashCode) ();
        x10aux::ref<x10::lang::Iterator<FMGL(T)> > (I::*iterator) ();
        FMGL(T) (I::*__apply) (x10_int);
        x10_int (I::*size) ();
        x10aux::ref<x10::lang::String> (I::*toString) ();
        x10aux::ref<x10::lang::String> (I::*typeName) ();
    };
    
    template <class R> static x10_boolean equals(x10aux::ref<R> _recv, x10aux::ref<x10::lang::Any> arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::lang::Sequence<FMGL(T)> >(_refRecv->_getITables())->equals))(arg0);
    }
    template <class R> static x10_boolean equals(R _recv, x10aux::ref<x10::lang::Any> arg0) {
        return R::_METHODS::equals(_recv, arg0);
    }
    template <class R> static x10_int hashCode(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::lang::Sequence<FMGL(T)> >(_refRecv->_getITables())->hashCode))();
    }
    template <class R> static x10_int hashCode(R _recv) {
        return R::_METHODS::hashCode(_recv);
    }
    template <class R> static x10aux::ref<x10::lang::Iterator<FMGL(T)> > iterator(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::lang::Sequence<FMGL(T)> >(_refRecv->_getITables())->iterator))();
    }
    template <class R> static x10aux::ref<x10::lang::Iterator<FMGL(T)> > iterator(R _recv) {
        return R::_METHODS::iterator(_recv);
    }
    template <class R> static FMGL(T) __apply(x10aux::ref<R> _recv, x10_int arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::lang::Sequence<FMGL(T)> >(_refRecv->_getITables())->__apply))(arg0);
    }
    template <class R> static FMGL(T) __apply(R _recv, x10_int arg0) {
        return R::_METHODS::__apply(_recv, arg0);
    }
    template <class R> static x10_int size(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::lang::Sequence<FMGL(T)> >(_refRecv->_getITables())->size))();
    }
    template <class R> static x10_int size(R _recv) {
        return R::_METHODS::size(_recv);
    }
    template <class R> static x10aux::ref<x10::lang::String> toString(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::lang::Sequence<FMGL(T)> >(_refRecv->_getITables())->toString))();
    }
    template <class R> static x10aux::ref<x10::lang::String> toString(R _recv) {
        return R::_METHODS::toString(_recv);
    }
    template <class R> static x10aux::ref<x10::lang::String> typeName(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::lang::Sequence<FMGL(T)> >(_refRecv->_getITables())->typeName))();
    }
    template <class R> static x10aux::ref<x10::lang::String> typeName(R _recv) {
        return R::_METHODS::typeName(_recv);
    }
    void _instance_init();
    
    
};
template <> class Sequence<void> {
    public:
    static x10aux::RuntimeType rtt;
    static const x10aux::RuntimeType* getRTT() { return & rtt; }
    
};

} } 
#endif // X10_LANG_SEQUENCE_H

namespace x10 { namespace lang { 
template<class FMGL(T)> class Sequence;
} } 

#ifndef X10_LANG_SEQUENCE_H_NODEPS
#define X10_LANG_SEQUENCE_H_NODEPS
#include <x10/lang/Any.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/Iterable.h>
#include <x10/lang/Int.h>
#include <x10/compiler/Native.h>
#ifndef X10_LANG_SEQUENCE_H_GENERICS
#define X10_LANG_SEQUENCE_H_GENERICS
#endif // X10_LANG_SEQUENCE_H_GENERICS
#ifndef X10_LANG_SEQUENCE_H_IMPLEMENTATION
#define X10_LANG_SEQUENCE_H_IMPLEMENTATION
#include <x10/lang/Sequence.h>



//#line 31 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/Sequence.x10": x10.ast.PropertyDecl_c
template<class FMGL(T)> void x10::lang::Sequence<FMGL(T)>::_instance_init() {
    _I_("Doing initialisation for class: x10::lang::Sequence<FMGL(T)>");
    
}

template<class FMGL(T)> x10aux::RuntimeType x10::lang::Sequence<FMGL(T)>::rtt;
template<class FMGL(T)> void x10::lang::Sequence<FMGL(T)>::_initRTT() {
    const x10aux::RuntimeType *canonical = x10aux::getRTT<x10::lang::Sequence<void> >();
    if (rtt.initStageOne(canonical)) return;
    const x10aux::RuntimeType* parents[3] = { x10aux::getRTT<x10::lang::Any>(), x10aux::getRTT<x10::lang::Fun_0_1<x10_int, FMGL(T)> >(), x10aux::getRTT<x10::lang::Iterable<FMGL(T)> >()};
    const x10aux::RuntimeType* params[1] = { x10aux::getRTT<FMGL(T)>()};
    x10aux::RuntimeType::Variance variances[1] = { x10aux::RuntimeType::covariant};
    const char *baseName = "x10.lang.Sequence";
    rtt.initStageTwo(baseName, x10aux::RuntimeType::interface_kind, 3, parents, 1, params, variances);
}
#endif // X10_LANG_SEQUENCE_H_IMPLEMENTATION
#endif // __X10_LANG_SEQUENCE_H_NODEPS
