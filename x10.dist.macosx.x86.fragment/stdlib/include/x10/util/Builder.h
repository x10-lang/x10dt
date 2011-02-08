#ifndef __X10_UTIL_BUILDER_H
#define __X10_UTIL_BUILDER_H

#include <x10rt.h>


#define X10_LANG_ANY_H_NODEPS
#include <x10/lang/Any.h>
#undef X10_LANG_ANY_H_NODEPS
namespace x10 { namespace util { 

template<class FMGL(Element), class FMGL(Collection)> class Builder;
template <> class Builder<void, void>;
template<class FMGL(Element), class FMGL(Collection)> class Builder   {
    public:
    RTT_H_DECLS_INTERFACE
    
    template <class I> struct itable {
        itable(x10aux::ref<x10::util::Builder<FMGL(Element), FMGL(Collection)> > (I::*add) (FMGL(Element)), x10_boolean (I::*equals) (x10aux::ref<x10::lang::Any>), x10_int (I::*hashCode) (), FMGL(Collection) (I::*result) (), x10aux::ref<x10::lang::String> (I::*toString) (), x10aux::ref<x10::lang::String> (I::*typeName) ()) : add(add), equals(equals), hashCode(hashCode), result(result), toString(toString), typeName(typeName) {}
        x10aux::ref<x10::util::Builder<FMGL(Element), FMGL(Collection)> > (I::*add) (FMGL(Element));
        x10_boolean (I::*equals) (x10aux::ref<x10::lang::Any>);
        x10_int (I::*hashCode) ();
        FMGL(Collection) (I::*result) ();
        x10aux::ref<x10::lang::String> (I::*toString) ();
        x10aux::ref<x10::lang::String> (I::*typeName) ();
    };
    
    template <class R> static x10aux::ref<x10::util::Builder<FMGL(Element), FMGL(Collection)> > add(x10aux::ref<R> _recv, FMGL(Element) arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::Builder<FMGL(Element), FMGL(Collection)> >(_refRecv->_getITables())->add))(arg0);
    }
    template <class R> static x10aux::ref<x10::util::Builder<FMGL(Element), FMGL(Collection)> > add(R _recv, FMGL(Element) arg0) {
        return R::_METHODS::add(_recv, arg0);
    }
    template <class R> static x10_boolean equals(x10aux::ref<R> _recv, x10aux::ref<x10::lang::Any> arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::Builder<FMGL(Element), FMGL(Collection)> >(_refRecv->_getITables())->equals))(arg0);
    }
    template <class R> static x10_boolean equals(R _recv, x10aux::ref<x10::lang::Any> arg0) {
        return R::_METHODS::equals(_recv, arg0);
    }
    template <class R> static x10_int hashCode(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::Builder<FMGL(Element), FMGL(Collection)> >(_refRecv->_getITables())->hashCode))();
    }
    template <class R> static x10_int hashCode(R _recv) {
        return R::_METHODS::hashCode(_recv);
    }
    template <class R> static FMGL(Collection) result(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::Builder<FMGL(Element), FMGL(Collection)> >(_refRecv->_getITables())->result))();
    }
    template <class R> static FMGL(Collection) result(R _recv) {
        return R::_METHODS::result(_recv);
    }
    template <class R> static x10aux::ref<x10::lang::String> toString(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::Builder<FMGL(Element), FMGL(Collection)> >(_refRecv->_getITables())->toString))();
    }
    template <class R> static x10aux::ref<x10::lang::String> toString(R _recv) {
        return R::_METHODS::toString(_recv);
    }
    template <class R> static x10aux::ref<x10::lang::String> typeName(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::Builder<FMGL(Element), FMGL(Collection)> >(_refRecv->_getITables())->typeName))();
    }
    template <class R> static x10aux::ref<x10::lang::String> typeName(R _recv) {
        return R::_METHODS::typeName(_recv);
    }
    void _instance_init();
    
    
};
template <> class Builder<void, void> {
    public:
    static x10aux::RuntimeType rtt;
    static const x10aux::RuntimeType* getRTT() { return & rtt; }
    
};

} } 
#endif // X10_UTIL_BUILDER_H

namespace x10 { namespace util { 
template<class FMGL(Element), class FMGL(Collection)> class Builder;
} } 

#ifndef X10_UTIL_BUILDER_H_NODEPS
#define X10_UTIL_BUILDER_H_NODEPS
#include <x10/lang/Any.h>
#ifndef X10_UTIL_BUILDER_H_GENERICS
#define X10_UTIL_BUILDER_H_GENERICS
#endif // X10_UTIL_BUILDER_H_GENERICS
#ifndef X10_UTIL_BUILDER_H_IMPLEMENTATION
#define X10_UTIL_BUILDER_H_IMPLEMENTATION
#include <x10/util/Builder.h>


template<class FMGL(Element), class FMGL(Collection)> void x10::util::Builder<FMGL(Element), FMGL(Collection)>::_instance_init() {
    _I_("Doing initialisation for class: x10::util::Builder<FMGL(Element), FMGL(Collection)>");
    
}

template<class FMGL(Element), class FMGL(Collection)> x10aux::RuntimeType x10::util::Builder<FMGL(Element), FMGL(Collection)>::rtt;
template<class FMGL(Element), class FMGL(Collection)> void x10::util::Builder<FMGL(Element), FMGL(Collection)>::_initRTT() {
    const x10aux::RuntimeType *canonical = x10aux::getRTT<x10::util::Builder<void, void> >();
    if (rtt.initStageOne(canonical)) return;
    const x10aux::RuntimeType* parents[1] = { x10aux::getRTT<x10::lang::Any>()};
    const x10aux::RuntimeType* params[2] = { x10aux::getRTT<FMGL(Element)>(), x10aux::getRTT<FMGL(Collection)>()};
    x10aux::RuntimeType::Variance variances[2] = { x10aux::RuntimeType::invariant, x10aux::RuntimeType::covariant};
    const char *baseName = "x10.util.Builder";
    rtt.initStageTwo(baseName, x10aux::RuntimeType::interface_kind, 1, parents, 2, params, variances);
}
#endif // X10_UTIL_BUILDER_H_IMPLEMENTATION
#endif // __X10_UTIL_BUILDER_H_NODEPS
