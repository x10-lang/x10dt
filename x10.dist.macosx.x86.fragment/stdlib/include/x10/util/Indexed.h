#ifndef __X10_UTIL_INDEXED_H
#define __X10_UTIL_INDEXED_H

#include <x10rt.h>


#define X10_LANG_ANY_H_NODEPS
#include <x10/lang/Any.h>
#undef X10_LANG_ANY_H_NODEPS
#define X10_UTIL_CONTAINER_H_NODEPS
#include <x10/util/Container.h>
#undef X10_UTIL_CONTAINER_H_NODEPS
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace util { 

template<class FMGL(T)> class Indexed;
template <> class Indexed<void>;
template<class FMGL(T)> class Indexed   {
    public:
    RTT_H_DECLS_INTERFACE
    
    template <class I> struct itable {
        itable(x10aux::ref<x10::util::Container<FMGL(T)> > (I::*clone) (), x10_boolean (I::*contains) (FMGL(T)), x10_boolean (I::*containsAll) (x10aux::ref<x10::util::Container<FMGL(T)> >), x10_boolean (I::*equals) (x10aux::ref<x10::lang::Any>), x10_int (I::*hashCode) (), x10_boolean (I::*isEmpty) (), x10aux::ref<x10::lang::Iterator<FMGL(T)> > (I::*iterator) (), FMGL(T) (I::*__apply) (x10_int), x10_int (I::*size) (), x10aux::ref<x10::lang::Rail<FMGL(T) > > (I::*toRail) (), x10aux::ref<x10::lang::String> (I::*toString) (), x10aux::ref<x10::lang::String> (I::*typeName) ()) : clone(clone), contains(contains), containsAll(containsAll), equals(equals), hashCode(hashCode), isEmpty(isEmpty), iterator(iterator), __apply(__apply), size(size), toRail(toRail), toString(toString), typeName(typeName) {}
        x10aux::ref<x10::util::Container<FMGL(T)> > (I::*clone) ();
        x10_boolean (I::*contains) (FMGL(T));
        x10_boolean (I::*containsAll) (x10aux::ref<x10::util::Container<FMGL(T)> >);
        x10_boolean (I::*equals) (x10aux::ref<x10::lang::Any>);
        x10_int (I::*hashCode) ();
        x10_boolean (I::*isEmpty) ();
        x10aux::ref<x10::lang::Iterator<FMGL(T)> > (I::*iterator) ();
        FMGL(T) (I::*__apply) (x10_int);
        x10_int (I::*size) ();
        x10aux::ref<x10::lang::Rail<FMGL(T) > > (I::*toRail) ();
        x10aux::ref<x10::lang::String> (I::*toString) ();
        x10aux::ref<x10::lang::String> (I::*typeName) ();
    };
    
    template <class R> static x10aux::ref<x10::util::Container<FMGL(T)> > clone(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::Indexed<FMGL(T)> >(_refRecv->_getITables())->clone))();
    }
    template <class R> static x10aux::ref<x10::util::Container<FMGL(T)> > clone(R _recv) {
        return R::_METHODS::clone(_recv);
    }
    template <class R> static x10_boolean contains(x10aux::ref<R> _recv, FMGL(T) arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::Indexed<FMGL(T)> >(_refRecv->_getITables())->contains))(arg0);
    }
    template <class R> static x10_boolean contains(R _recv, FMGL(T) arg0) {
        return R::_METHODS::contains(_recv, arg0);
    }
    template <class R> static x10_boolean containsAll(x10aux::ref<R> _recv, x10aux::ref<x10::util::Container<FMGL(T)> > arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::Indexed<FMGL(T)> >(_refRecv->_getITables())->containsAll))(arg0);
    }
    template <class R> static x10_boolean containsAll(R _recv, x10aux::ref<x10::util::Container<FMGL(T)> > arg0) {
        return R::_METHODS::containsAll(_recv, arg0);
    }
    template <class R> static x10_boolean equals(x10aux::ref<R> _recv, x10aux::ref<x10::lang::Any> arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::Indexed<FMGL(T)> >(_refRecv->_getITables())->equals))(arg0);
    }
    template <class R> static x10_boolean equals(R _recv, x10aux::ref<x10::lang::Any> arg0) {
        return R::_METHODS::equals(_recv, arg0);
    }
    template <class R> static x10_int hashCode(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::Indexed<FMGL(T)> >(_refRecv->_getITables())->hashCode))();
    }
    template <class R> static x10_int hashCode(R _recv) {
        return R::_METHODS::hashCode(_recv);
    }
    template <class R> static x10_boolean isEmpty(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::Indexed<FMGL(T)> >(_refRecv->_getITables())->isEmpty))();
    }
    template <class R> static x10_boolean isEmpty(R _recv) {
        return R::_METHODS::isEmpty(_recv);
    }
    template <class R> static x10aux::ref<x10::lang::Iterator<FMGL(T)> > iterator(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::Indexed<FMGL(T)> >(_refRecv->_getITables())->iterator))();
    }
    template <class R> static x10aux::ref<x10::lang::Iterator<FMGL(T)> > iterator(R _recv) {
        return R::_METHODS::iterator(_recv);
    }
    template <class R> static FMGL(T) __apply(x10aux::ref<R> _recv, x10_int arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::Indexed<FMGL(T)> >(_refRecv->_getITables())->__apply))(arg0);
    }
    template <class R> static FMGL(T) __apply(R _recv, x10_int arg0) {
        return R::_METHODS::__apply(_recv, arg0);
    }
    template <class R> static x10_int size(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::Indexed<FMGL(T)> >(_refRecv->_getITables())->size))();
    }
    template <class R> static x10_int size(R _recv) {
        return R::_METHODS::size(_recv);
    }
    template <class R> static x10aux::ref<x10::lang::Rail<FMGL(T) > > toRail(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::Indexed<FMGL(T)> >(_refRecv->_getITables())->toRail))();
    }
    template <class R> static x10aux::ref<x10::lang::Rail<FMGL(T) > > toRail(R _recv) {
        return R::_METHODS::toRail(_recv);
    }
    template <class R> static x10aux::ref<x10::lang::String> toString(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::Indexed<FMGL(T)> >(_refRecv->_getITables())->toString))();
    }
    template <class R> static x10aux::ref<x10::lang::String> toString(R _recv) {
        return R::_METHODS::toString(_recv);
    }
    template <class R> static x10aux::ref<x10::lang::String> typeName(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::Indexed<FMGL(T)> >(_refRecv->_getITables())->typeName))();
    }
    template <class R> static x10aux::ref<x10::lang::String> typeName(R _recv) {
        return R::_METHODS::typeName(_recv);
    }
    void _instance_init();
    
    
};
template <> class Indexed<void> {
    public:
    static x10aux::RuntimeType rtt;
    static const x10aux::RuntimeType* getRTT() { return & rtt; }
    
};

} } 
#endif // X10_UTIL_INDEXED_H

namespace x10 { namespace util { 
template<class FMGL(T)> class Indexed;
} } 

#ifndef X10_UTIL_INDEXED_H_NODEPS
#define X10_UTIL_INDEXED_H_NODEPS
#include <x10/lang/Any.h>
#include <x10/util/Container.h>
#include <x10/lang/Int.h>
#ifndef X10_UTIL_INDEXED_H_GENERICS
#define X10_UTIL_INDEXED_H_GENERICS
#endif // X10_UTIL_INDEXED_H_GENERICS
#ifndef X10_UTIL_INDEXED_H_IMPLEMENTATION
#define X10_UTIL_INDEXED_H_IMPLEMENTATION
#include <x10/util/Indexed.h>


template<class FMGL(T)> void x10::util::Indexed<FMGL(T)>::_instance_init() {
    _I_("Doing initialisation for class: x10::util::Indexed<FMGL(T)>");
    
}

template<class FMGL(T)> x10aux::RuntimeType x10::util::Indexed<FMGL(T)>::rtt;
template<class FMGL(T)> void x10::util::Indexed<FMGL(T)>::_initRTT() {
    const x10aux::RuntimeType *canonical = x10aux::getRTT<x10::util::Indexed<void> >();
    if (rtt.initStageOne(canonical)) return;
    const x10aux::RuntimeType* parents[2] = { x10aux::getRTT<x10::lang::Any>(), x10aux::getRTT<x10::util::Container<FMGL(T)> >()};
    const x10aux::RuntimeType* params[1] = { x10aux::getRTT<FMGL(T)>()};
    x10aux::RuntimeType::Variance variances[1] = { x10aux::RuntimeType::invariant};
    const char *baseName = "x10.util.Indexed";
    rtt.initStageTwo(baseName, x10aux::RuntimeType::interface_kind, 2, parents, 1, params, variances);
}
#endif // X10_UTIL_INDEXED_H_IMPLEMENTATION
#endif // __X10_UTIL_INDEXED_H_NODEPS
