#ifndef __X10_UTIL_MAP__ENTRY_H
#define __X10_UTIL_MAP__ENTRY_H

#include <x10rt.h>


#define X10_LANG_ANY_H_NODEPS
#include <x10/lang/Any.h>
#undef X10_LANG_ANY_H_NODEPS
namespace x10 { namespace util { 

template<class FMGL(Key), class FMGL(Val)> class Map__Entry;
template <> class Map__Entry<void, void>;
template<class FMGL(Key), class FMGL(Val)> class Map__Entry   {
    public:
    RTT_H_DECLS_INTERFACE
    
    template <class I> struct itable {
        itable(x10_boolean (I::*equals) (x10aux::ref<x10::lang::Any>), FMGL(Key) (I::*getKey) (), FMGL(Val) (I::*getValue) (), x10_int (I::*hashCode) (), void (I::*setValue) (FMGL(Val)), x10aux::ref<x10::lang::String> (I::*toString) (), x10aux::ref<x10::lang::String> (I::*typeName) ()) : equals(equals), getKey(getKey), getValue(getValue), hashCode(hashCode), setValue(setValue), toString(toString), typeName(typeName) {}
        x10_boolean (I::*equals) (x10aux::ref<x10::lang::Any>);
        FMGL(Key) (I::*getKey) ();
        FMGL(Val) (I::*getValue) ();
        x10_int (I::*hashCode) ();
        void (I::*setValue) (FMGL(Val));
        x10aux::ref<x10::lang::String> (I::*toString) ();
        x10aux::ref<x10::lang::String> (I::*typeName) ();
    };
    
    template <class R> static x10_boolean equals(x10aux::ref<R> _recv, x10aux::ref<x10::lang::Any> arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::Map__Entry<FMGL(Key), FMGL(Val)> >(_refRecv->_getITables())->equals))(arg0);
    }
    template <class R> static x10_boolean equals(R _recv, x10aux::ref<x10::lang::Any> arg0) {
        return R::_METHODS::equals(_recv, arg0);
    }
    template <class R> static FMGL(Key) getKey(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::Map__Entry<FMGL(Key), FMGL(Val)> >(_refRecv->_getITables())->getKey))();
    }
    template <class R> static FMGL(Key) getKey(R _recv) {
        return R::_METHODS::getKey(_recv);
    }
    template <class R> static FMGL(Val) getValue(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::Map__Entry<FMGL(Key), FMGL(Val)> >(_refRecv->_getITables())->getValue))();
    }
    template <class R> static FMGL(Val) getValue(R _recv) {
        return R::_METHODS::getValue(_recv);
    }
    template <class R> static x10_int hashCode(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::Map__Entry<FMGL(Key), FMGL(Val)> >(_refRecv->_getITables())->hashCode))();
    }
    template <class R> static x10_int hashCode(R _recv) {
        return R::_METHODS::hashCode(_recv);
    }
    template <class R> static void setValue(x10aux::ref<R> _recv, FMGL(Val) arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        (_refRecv.operator->()->*(x10aux::findITable<x10::util::Map__Entry<FMGL(Key), FMGL(Val)> >(_refRecv->_getITables())->setValue))(arg0);
    }
    template <class R> static void setValue(R _recv, FMGL(Val) arg0) {
        R::_METHODS::setValue(_recv, arg0);
    }
    template <class R> static x10aux::ref<x10::lang::String> toString(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::Map__Entry<FMGL(Key), FMGL(Val)> >(_refRecv->_getITables())->toString))();
    }
    template <class R> static x10aux::ref<x10::lang::String> toString(R _recv) {
        return R::_METHODS::toString(_recv);
    }
    template <class R> static x10aux::ref<x10::lang::String> typeName(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::Map__Entry<FMGL(Key), FMGL(Val)> >(_refRecv->_getITables())->typeName))();
    }
    template <class R> static x10aux::ref<x10::lang::String> typeName(R _recv) {
        return R::_METHODS::typeName(_recv);
    }
    void _instance_init();
    
    
};
template <> class Map__Entry<void, void> {
    public:
    static x10aux::RuntimeType rtt;
    static const x10aux::RuntimeType* getRTT() { return & rtt; }
    
};

} } 
#endif // X10_UTIL_MAP__ENTRY_H

namespace x10 { namespace util { 
template<class FMGL(Key), class FMGL(Val)> class Map__Entry;
} } 

#ifndef X10_UTIL_MAP__ENTRY_H_NODEPS
#define X10_UTIL_MAP__ENTRY_H_NODEPS
#include <x10/lang/Any.h>
#ifndef X10_UTIL_MAP__ENTRY_H_GENERICS
#define X10_UTIL_MAP__ENTRY_H_GENERICS
#endif // X10_UTIL_MAP__ENTRY_H_GENERICS
#ifndef X10_UTIL_MAP__ENTRY_H_IMPLEMENTATION
#define X10_UTIL_MAP__ENTRY_H_IMPLEMENTATION
#include <x10/util/Map__Entry.h>


template<class FMGL(Key), class FMGL(Val)> void x10::util::Map__Entry<FMGL(Key), FMGL(Val)>::_instance_init() {
    _I_("Doing initialisation for class: x10::util::Map__Entry<FMGL(Key), FMGL(Val)>");
    
}

template<class FMGL(Key), class FMGL(Val)> x10aux::RuntimeType x10::util::Map__Entry<FMGL(Key), FMGL(Val)>::rtt;
template<class FMGL(Key), class FMGL(Val)> void x10::util::Map__Entry<FMGL(Key), FMGL(Val)>::_initRTT() {
    const x10aux::RuntimeType *canonical = x10aux::getRTT<x10::util::Map__Entry<void, void> >();
    if (rtt.initStageOne(canonical)) return;
    const x10aux::RuntimeType* parents[1] = { x10aux::getRTT<x10::lang::Any>()};
    const x10aux::RuntimeType* params[2] = { x10aux::getRTT<FMGL(Key)>(), x10aux::getRTT<FMGL(Val)>()};
    x10aux::RuntimeType::Variance variances[2] = { x10aux::RuntimeType::invariant, x10aux::RuntimeType::invariant};
    const char *baseName = "x10.util.Map.Entry";
    rtt.initStageTwo(baseName, x10aux::RuntimeType::interface_kind, 1, parents, 2, params, variances);
}
#endif // X10_UTIL_MAP__ENTRY_H_IMPLEMENTATION
#endif // __X10_UTIL_MAP__ENTRY_H_NODEPS
