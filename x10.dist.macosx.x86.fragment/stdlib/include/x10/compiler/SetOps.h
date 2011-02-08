#ifndef __X10_COMPILER_SETOPS_H
#define __X10_COMPILER_SETOPS_H

#include <x10rt.h>


#define X10_LANG_ANY_H_NODEPS
#include <x10/lang/Any.h>
#undef X10_LANG_ANY_H_NODEPS
namespace x10 { namespace compiler { 

template<class FMGL(T)> class SetOps;
template <> class SetOps<void>;
template<class FMGL(T)> class SetOps   {
    public:
    RTT_H_DECLS_INTERFACE
    
    template <class I> struct itable {
        itable(FMGL(T) (I::*__and) (FMGL(T)), FMGL(T) (I::*__minus) (FMGL(T)), FMGL(T) (I::*__not) (), FMGL(T) (I::*__or) (FMGL(T)), x10_boolean (I::*equals) (x10aux::ref<x10::lang::Any>), x10_int (I::*hashCode) (), x10aux::ref<x10::lang::String> (I::*toString) (), x10aux::ref<x10::lang::String> (I::*typeName) ()) : __and(__and), __minus(__minus), __not(__not), __or(__or), equals(equals), hashCode(hashCode), toString(toString), typeName(typeName) {}
        FMGL(T) (I::*__and) (FMGL(T));
        FMGL(T) (I::*__minus) (FMGL(T));
        FMGL(T) (I::*__not) ();
        FMGL(T) (I::*__or) (FMGL(T));
        x10_boolean (I::*equals) (x10aux::ref<x10::lang::Any>);
        x10_int (I::*hashCode) ();
        x10aux::ref<x10::lang::String> (I::*toString) ();
        x10aux::ref<x10::lang::String> (I::*typeName) ();
    };
    
    template <class R> static FMGL(T) __and(x10aux::ref<R> _recv, FMGL(T) arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::compiler::SetOps<FMGL(T)> >(_refRecv->_getITables())->__and))(arg0);
    }
    template <class R> static FMGL(T) __and(R _recv, FMGL(T) arg0) {
        return R::_METHODS::__and(_recv, arg0);
    }
    template <class R> static FMGL(T) __minus(x10aux::ref<R> _recv, FMGL(T) arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::compiler::SetOps<FMGL(T)> >(_refRecv->_getITables())->__minus))(arg0);
    }
    template <class R> static FMGL(T) __minus(R _recv, FMGL(T) arg0) {
        return R::_METHODS::__minus(_recv, arg0);
    }
    template <class R> static FMGL(T) __not(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::compiler::SetOps<FMGL(T)> >(_refRecv->_getITables())->__not))();
    }
    template <class R> static FMGL(T) __not(R _recv) {
        return R::_METHODS::__not(_recv);
    }
    template <class R> static FMGL(T) __or(x10aux::ref<R> _recv, FMGL(T) arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::compiler::SetOps<FMGL(T)> >(_refRecv->_getITables())->__or))(arg0);
    }
    template <class R> static FMGL(T) __or(R _recv, FMGL(T) arg0) {
        return R::_METHODS::__or(_recv, arg0);
    }
    template <class R> static x10_boolean equals(x10aux::ref<R> _recv, x10aux::ref<x10::lang::Any> arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::compiler::SetOps<FMGL(T)> >(_refRecv->_getITables())->equals))(arg0);
    }
    template <class R> static x10_boolean equals(R _recv, x10aux::ref<x10::lang::Any> arg0) {
        return R::_METHODS::equals(_recv, arg0);
    }
    template <class R> static x10_int hashCode(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::compiler::SetOps<FMGL(T)> >(_refRecv->_getITables())->hashCode))();
    }
    template <class R> static x10_int hashCode(R _recv) {
        return R::_METHODS::hashCode(_recv);
    }
    template <class R> static x10aux::ref<x10::lang::String> toString(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::compiler::SetOps<FMGL(T)> >(_refRecv->_getITables())->toString))();
    }
    template <class R> static x10aux::ref<x10::lang::String> toString(R _recv) {
        return R::_METHODS::toString(_recv);
    }
    template <class R> static x10aux::ref<x10::lang::String> typeName(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::compiler::SetOps<FMGL(T)> >(_refRecv->_getITables())->typeName))();
    }
    template <class R> static x10aux::ref<x10::lang::String> typeName(R _recv) {
        return R::_METHODS::typeName(_recv);
    }
    void _instance_init();
    
    
};
template <> class SetOps<void> {
    public:
    static x10aux::RuntimeType rtt;
    static const x10aux::RuntimeType* getRTT() { return & rtt; }
    
};

} } 
#endif // X10_COMPILER_SETOPS_H

namespace x10 { namespace compiler { 
template<class FMGL(T)> class SetOps;
} } 

#ifndef X10_COMPILER_SETOPS_H_NODEPS
#define X10_COMPILER_SETOPS_H_NODEPS
#include <x10/lang/Any.h>
#ifndef X10_COMPILER_SETOPS_H_GENERICS
#define X10_COMPILER_SETOPS_H_GENERICS
#endif // X10_COMPILER_SETOPS_H_GENERICS
#ifndef X10_COMPILER_SETOPS_H_IMPLEMENTATION
#define X10_COMPILER_SETOPS_H_IMPLEMENTATION
#include <x10/compiler/SetOps.h>


template<class FMGL(T)> void x10::compiler::SetOps<FMGL(T)>::_instance_init() {
    _I_("Doing initialisation for class: x10::compiler::SetOps<FMGL(T)>");
    
}

template<class FMGL(T)> x10aux::RuntimeType x10::compiler::SetOps<FMGL(T)>::rtt;
template<class FMGL(T)> void x10::compiler::SetOps<FMGL(T)>::_initRTT() {
    const x10aux::RuntimeType *canonical = x10aux::getRTT<x10::compiler::SetOps<void> >();
    if (rtt.initStageOne(canonical)) return;
    const x10aux::RuntimeType* parents[1] = { x10aux::getRTT<x10::lang::Any>()};
    const x10aux::RuntimeType* params[1] = { x10aux::getRTT<FMGL(T)>()};
    x10aux::RuntimeType::Variance variances[1] = { x10aux::RuntimeType::invariant};
    const char *baseName = "x10.compiler.SetOps";
    rtt.initStageTwo(baseName, x10aux::RuntimeType::interface_kind, 1, parents, 1, params, variances);
}
#endif // X10_COMPILER_SETOPS_H_IMPLEMENTATION
#endif // __X10_COMPILER_SETOPS_H_NODEPS
