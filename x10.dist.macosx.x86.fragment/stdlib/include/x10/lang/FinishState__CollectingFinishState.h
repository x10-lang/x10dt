#ifndef __X10_LANG_FINISHSTATE__COLLECTINGFINISHSTATE_H
#define __X10_LANG_FINISHSTATE__COLLECTINGFINISHSTATE_H

#include <x10rt.h>


#define X10_LANG_ANY_H_NODEPS
#include <x10/lang/Any.h>
#undef X10_LANG_ANY_H_NODEPS
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace lang { 

template<class FMGL(T)> class FinishState__CollectingFinishState;
template <> class FinishState__CollectingFinishState<void>;
template<class FMGL(T)> class FinishState__CollectingFinishState   {
    public:
    RTT_H_DECLS_INTERFACE
    
    template <class I> struct itable {
        itable(void (I::*accept) (FMGL(T), x10_int), x10_boolean (I::*equals) (x10aux::ref<x10::lang::Any>), x10_int (I::*hashCode) (), x10aux::ref<x10::lang::String> (I::*toString) (), x10aux::ref<x10::lang::String> (I::*typeName) ()) : accept(accept), equals(equals), hashCode(hashCode), toString(toString), typeName(typeName) {}
        void (I::*accept) (FMGL(T), x10_int);
        x10_boolean (I::*equals) (x10aux::ref<x10::lang::Any>);
        x10_int (I::*hashCode) ();
        x10aux::ref<x10::lang::String> (I::*toString) ();
        x10aux::ref<x10::lang::String> (I::*typeName) ();
    };
    
    template <class R> static void accept(x10aux::ref<R> _recv, FMGL(T) arg0, x10_int arg1) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        (_refRecv.operator->()->*(x10aux::findITable<x10::lang::FinishState__CollectingFinishState<FMGL(T)> >(_refRecv->_getITables())->accept))(arg0, arg1);
    }
    template <class R> static void accept(R _recv, FMGL(T) arg0, x10_int arg1) {
        R::_METHODS::accept(_recv, arg0, arg1);
    }
    template <class R> static x10_boolean equals(x10aux::ref<R> _recv, x10aux::ref<x10::lang::Any> arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::lang::FinishState__CollectingFinishState<FMGL(T)> >(_refRecv->_getITables())->equals))(arg0);
    }
    template <class R> static x10_boolean equals(R _recv, x10aux::ref<x10::lang::Any> arg0) {
        return R::_METHODS::equals(_recv, arg0);
    }
    template <class R> static x10_int hashCode(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::lang::FinishState__CollectingFinishState<FMGL(T)> >(_refRecv->_getITables())->hashCode))();
    }
    template <class R> static x10_int hashCode(R _recv) {
        return R::_METHODS::hashCode(_recv);
    }
    template <class R> static x10aux::ref<x10::lang::String> toString(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::lang::FinishState__CollectingFinishState<FMGL(T)> >(_refRecv->_getITables())->toString))();
    }
    template <class R> static x10aux::ref<x10::lang::String> toString(R _recv) {
        return R::_METHODS::toString(_recv);
    }
    template <class R> static x10aux::ref<x10::lang::String> typeName(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::lang::FinishState__CollectingFinishState<FMGL(T)> >(_refRecv->_getITables())->typeName))();
    }
    template <class R> static x10aux::ref<x10::lang::String> typeName(R _recv) {
        return R::_METHODS::typeName(_recv);
    }
    void _instance_init();
    
    
};
template <> class FinishState__CollectingFinishState<void> {
    public:
    static x10aux::RuntimeType rtt;
    static const x10aux::RuntimeType* getRTT() { return & rtt; }
    
};

} } 
#endif // X10_LANG_FINISHSTATE__COLLECTINGFINISHSTATE_H

namespace x10 { namespace lang { 
template<class FMGL(T)> class FinishState__CollectingFinishState;
} } 

#ifndef X10_LANG_FINISHSTATE__COLLECTINGFINISHSTATE_H_NODEPS
#define X10_LANG_FINISHSTATE__COLLECTINGFINISHSTATE_H_NODEPS
#include <x10/lang/Any.h>
#include <x10/lang/Int.h>
#ifndef X10_LANG_FINISHSTATE__COLLECTINGFINISHSTATE_H_GENERICS
#define X10_LANG_FINISHSTATE__COLLECTINGFINISHSTATE_H_GENERICS
#endif // X10_LANG_FINISHSTATE__COLLECTINGFINISHSTATE_H_GENERICS
#ifndef X10_LANG_FINISHSTATE__COLLECTINGFINISHSTATE_H_IMPLEMENTATION
#define X10_LANG_FINISHSTATE__COLLECTINGFINISHSTATE_H_IMPLEMENTATION
#include <x10/lang/FinishState__CollectingFinishState.h>


template<class FMGL(T)> void x10::lang::FinishState__CollectingFinishState<FMGL(T)>::_instance_init() {
    _I_("Doing initialisation for class: x10::lang::FinishState__CollectingFinishState<FMGL(T)>");
    
}

template<class FMGL(T)> x10aux::RuntimeType x10::lang::FinishState__CollectingFinishState<FMGL(T)>::rtt;
template<class FMGL(T)> void x10::lang::FinishState__CollectingFinishState<FMGL(T)>::_initRTT() {
    const x10aux::RuntimeType *canonical = x10aux::getRTT<x10::lang::FinishState__CollectingFinishState<void> >();
    if (rtt.initStageOne(canonical)) return;
    const x10aux::RuntimeType* parents[1] = { x10aux::getRTT<x10::lang::Any>()};
    const x10aux::RuntimeType* params[1] = { x10aux::getRTT<FMGL(T)>()};
    x10aux::RuntimeType::Variance variances[1] = { x10aux::RuntimeType::invariant};
    const char *baseName = "x10.lang.FinishState.CollectingFinishState";
    rtt.initStageTwo(baseName, x10aux::RuntimeType::interface_kind, 1, parents, 1, params, variances);
}
#endif // X10_LANG_FINISHSTATE__COLLECTINGFINISHSTATE_H_IMPLEMENTATION
#endif // __X10_LANG_FINISHSTATE__COLLECTINGFINISHSTATE_H_NODEPS
