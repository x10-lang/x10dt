#ifndef __X10_UTIL_COLLECTION_H
#define __X10_UTIL_COLLECTION_H

#include <x10rt.h>


#define X10_LANG_ANY_H_NODEPS
#include <x10/lang/Any.h>
#undef X10_LANG_ANY_H_NODEPS
#define X10_UTIL_CONTAINER_H_NODEPS
#include <x10/util/Container.h>
#undef X10_UTIL_CONTAINER_H_NODEPS
namespace x10 { namespace lang { 
class Boolean;
} } 
#include <x10/lang/Boolean.struct_h>
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace util { 

template<class FMGL(T)> class Collection;
template <> class Collection<void>;
template<class FMGL(T)> class Collection   {
    public:
    RTT_H_DECLS_INTERFACE
    
    template <class I> struct itable {
        itable(x10_boolean (I::*add) (FMGL(T)), x10_boolean (I::*addAll) (x10aux::ref<x10::util::Container<FMGL(T)> >), x10_boolean (I::*addAllWhere) (x10aux::ref<x10::util::Container<FMGL(T)> >, x10aux::ref<x10::lang::Fun_0_1<FMGL(T), x10_boolean> >), void (I::*clear) (), x10aux::ref<x10::util::Container<FMGL(T)> > (I::*clone) (), x10_boolean (I::*contains) (FMGL(T)), x10_boolean (I::*containsAll) (x10aux::ref<x10::util::Container<FMGL(T)> >), x10_boolean (I::*equals) (x10aux::ref<x10::lang::Any>), x10_int (I::*hashCode) (), x10_boolean (I::*isEmpty) (), x10aux::ref<x10::lang::Iterator<FMGL(T)> > (I::*iterator) (), x10_boolean (I::*remove) (FMGL(T)), x10_boolean (I::*removeAll) (x10aux::ref<x10::util::Container<FMGL(T)> >), x10_boolean (I::*removeAllWhere) (x10aux::ref<x10::lang::Fun_0_1<FMGL(T), x10_boolean> >), x10_boolean (I::*retainAll) (x10aux::ref<x10::util::Container<FMGL(T)> >), x10_int (I::*size) (), x10aux::ref<x10::lang::Rail<FMGL(T) > > (I::*toRail) (), x10aux::ref<x10::lang::String> (I::*toString) (), x10aux::ref<x10::lang::String> (I::*typeName) ()) : add(add), addAll(addAll), addAllWhere(addAllWhere), clear(clear), clone(clone), contains(contains), containsAll(containsAll), equals(equals), hashCode(hashCode), isEmpty(isEmpty), iterator(iterator), remove(remove), removeAll(removeAll), removeAllWhere(removeAllWhere), retainAll(retainAll), size(size), toRail(toRail), toString(toString), typeName(typeName) {}
        x10_boolean (I::*add) (FMGL(T));
        x10_boolean (I::*addAll) (x10aux::ref<x10::util::Container<FMGL(T)> >);
        x10_boolean (I::*addAllWhere) (x10aux::ref<x10::util::Container<FMGL(T)> >, x10aux::ref<x10::lang::Fun_0_1<FMGL(T), x10_boolean> >);
        void (I::*clear) ();
        x10aux::ref<x10::util::Container<FMGL(T)> > (I::*clone) ();
        x10_boolean (I::*contains) (FMGL(T));
        x10_boolean (I::*containsAll) (x10aux::ref<x10::util::Container<FMGL(T)> >);
        x10_boolean (I::*equals) (x10aux::ref<x10::lang::Any>);
        x10_int (I::*hashCode) ();
        x10_boolean (I::*isEmpty) ();
        x10aux::ref<x10::lang::Iterator<FMGL(T)> > (I::*iterator) ();
        x10_boolean (I::*remove) (FMGL(T));
        x10_boolean (I::*removeAll) (x10aux::ref<x10::util::Container<FMGL(T)> >);
        x10_boolean (I::*removeAllWhere) (x10aux::ref<x10::lang::Fun_0_1<FMGL(T), x10_boolean> >);
        x10_boolean (I::*retainAll) (x10aux::ref<x10::util::Container<FMGL(T)> >);
        x10_int (I::*size) ();
        x10aux::ref<x10::lang::Rail<FMGL(T) > > (I::*toRail) ();
        x10aux::ref<x10::lang::String> (I::*toString) ();
        x10aux::ref<x10::lang::String> (I::*typeName) ();
    };
    
    template <class R> static x10_boolean add(x10aux::ref<R> _recv, FMGL(T) arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::Collection<FMGL(T)> >(_refRecv->_getITables())->add))(arg0);
    }
    template <class R> static x10_boolean add(R _recv, FMGL(T) arg0) {
        return R::_METHODS::add(_recv, arg0);
    }
    template <class R> static x10_boolean addAll(x10aux::ref<R> _recv, x10aux::ref<x10::util::Container<FMGL(T)> > arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::Collection<FMGL(T)> >(_refRecv->_getITables())->addAll))(arg0);
    }
    template <class R> static x10_boolean addAll(R _recv, x10aux::ref<x10::util::Container<FMGL(T)> > arg0) {
        return R::_METHODS::addAll(_recv, arg0);
    }
    template <class R> static x10_boolean addAllWhere(x10aux::ref<R> _recv, x10aux::ref<x10::util::Container<FMGL(T)> > arg0, x10aux::ref<x10::lang::Fun_0_1<FMGL(T), x10_boolean> > arg1) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::Collection<FMGL(T)> >(_refRecv->_getITables())->addAllWhere))(arg0, arg1);
    }
    template <class R> static x10_boolean addAllWhere(R _recv, x10aux::ref<x10::util::Container<FMGL(T)> > arg0, x10aux::ref<x10::lang::Fun_0_1<FMGL(T), x10_boolean> > arg1) {
        return R::_METHODS::addAllWhere(_recv, arg0, arg1);
    }
    template <class R> static void clear(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        (_refRecv.operator->()->*(x10aux::findITable<x10::util::Collection<FMGL(T)> >(_refRecv->_getITables())->clear))();
    }
    template <class R> static void clear(R _recv) {
        R::_METHODS::clear(_recv);
    }
    template <class R> static x10aux::ref<x10::util::Collection<FMGL(T)> > clone(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::Collection<FMGL(T)> >(_refRecv->_getITables())->clone))();
    }
    template <class R> static x10aux::ref<x10::util::Collection<FMGL(T)> > clone(R _recv) {
        return R::_METHODS::clone(_recv);
    }
    template <class R> static x10_boolean contains(x10aux::ref<R> _recv, FMGL(T) arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::Collection<FMGL(T)> >(_refRecv->_getITables())->contains))(arg0);
    }
    template <class R> static x10_boolean contains(R _recv, FMGL(T) arg0) {
        return R::_METHODS::contains(_recv, arg0);
    }
    template <class R> static x10_boolean containsAll(x10aux::ref<R> _recv, x10aux::ref<x10::util::Container<FMGL(T)> > arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::Collection<FMGL(T)> >(_refRecv->_getITables())->containsAll))(arg0);
    }
    template <class R> static x10_boolean containsAll(R _recv, x10aux::ref<x10::util::Container<FMGL(T)> > arg0) {
        return R::_METHODS::containsAll(_recv, arg0);
    }
    template <class R> static x10_boolean equals(x10aux::ref<R> _recv, x10aux::ref<x10::lang::Any> arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::Collection<FMGL(T)> >(_refRecv->_getITables())->equals))(arg0);
    }
    template <class R> static x10_boolean equals(R _recv, x10aux::ref<x10::lang::Any> arg0) {
        return R::_METHODS::equals(_recv, arg0);
    }
    template <class R> static x10_int hashCode(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::Collection<FMGL(T)> >(_refRecv->_getITables())->hashCode))();
    }
    template <class R> static x10_int hashCode(R _recv) {
        return R::_METHODS::hashCode(_recv);
    }
    template <class R> static x10_boolean isEmpty(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::Collection<FMGL(T)> >(_refRecv->_getITables())->isEmpty))();
    }
    template <class R> static x10_boolean isEmpty(R _recv) {
        return R::_METHODS::isEmpty(_recv);
    }
    template <class R> static x10aux::ref<x10::lang::Iterator<FMGL(T)> > iterator(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::Collection<FMGL(T)> >(_refRecv->_getITables())->iterator))();
    }
    template <class R> static x10aux::ref<x10::lang::Iterator<FMGL(T)> > iterator(R _recv) {
        return R::_METHODS::iterator(_recv);
    }
    template <class R> static x10_boolean remove(x10aux::ref<R> _recv, FMGL(T) arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::Collection<FMGL(T)> >(_refRecv->_getITables())->remove))(arg0);
    }
    template <class R> static x10_boolean remove(R _recv, FMGL(T) arg0) {
        return R::_METHODS::remove(_recv, arg0);
    }
    template <class R> static x10_boolean removeAll(x10aux::ref<R> _recv, x10aux::ref<x10::util::Container<FMGL(T)> > arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::Collection<FMGL(T)> >(_refRecv->_getITables())->removeAll))(arg0);
    }
    template <class R> static x10_boolean removeAll(R _recv, x10aux::ref<x10::util::Container<FMGL(T)> > arg0) {
        return R::_METHODS::removeAll(_recv, arg0);
    }
    template <class R> static x10_boolean removeAllWhere(x10aux::ref<R> _recv, x10aux::ref<x10::lang::Fun_0_1<FMGL(T), x10_boolean> > arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::Collection<FMGL(T)> >(_refRecv->_getITables())->removeAllWhere))(arg0);
    }
    template <class R> static x10_boolean removeAllWhere(R _recv, x10aux::ref<x10::lang::Fun_0_1<FMGL(T), x10_boolean> > arg0) {
        return R::_METHODS::removeAllWhere(_recv, arg0);
    }
    template <class R> static x10_boolean retainAll(x10aux::ref<R> _recv, x10aux::ref<x10::util::Container<FMGL(T)> > arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::Collection<FMGL(T)> >(_refRecv->_getITables())->retainAll))(arg0);
    }
    template <class R> static x10_boolean retainAll(R _recv, x10aux::ref<x10::util::Container<FMGL(T)> > arg0) {
        return R::_METHODS::retainAll(_recv, arg0);
    }
    template <class R> static x10_int size(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::Collection<FMGL(T)> >(_refRecv->_getITables())->size))();
    }
    template <class R> static x10_int size(R _recv) {
        return R::_METHODS::size(_recv);
    }
    template <class R> static x10aux::ref<x10::lang::Rail<FMGL(T) > > toRail(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::Collection<FMGL(T)> >(_refRecv->_getITables())->toRail))();
    }
    template <class R> static x10aux::ref<x10::lang::Rail<FMGL(T) > > toRail(R _recv) {
        return R::_METHODS::toRail(_recv);
    }
    template <class R> static x10aux::ref<x10::lang::String> toString(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::Collection<FMGL(T)> >(_refRecv->_getITables())->toString))();
    }
    template <class R> static x10aux::ref<x10::lang::String> toString(R _recv) {
        return R::_METHODS::toString(_recv);
    }
    template <class R> static x10aux::ref<x10::lang::String> typeName(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::Collection<FMGL(T)> >(_refRecv->_getITables())->typeName))();
    }
    template <class R> static x10aux::ref<x10::lang::String> typeName(R _recv) {
        return R::_METHODS::typeName(_recv);
    }
    void _instance_init();
    
    
};
template <> class Collection<void> {
    public:
    static x10aux::RuntimeType rtt;
    static const x10aux::RuntimeType* getRTT() { return & rtt; }
    
};

} } 
#endif // X10_UTIL_COLLECTION_H

namespace x10 { namespace util { 
template<class FMGL(T)> class Collection;
} } 

#ifndef X10_UTIL_COLLECTION_H_NODEPS
#define X10_UTIL_COLLECTION_H_NODEPS
#include <x10/lang/Any.h>
#include <x10/util/Container.h>
#include <x10/lang/Boolean.h>
#include <x10/lang/Fun_0_1.h>
#ifndef X10_UTIL_COLLECTION_H_GENERICS
#define X10_UTIL_COLLECTION_H_GENERICS
#endif // X10_UTIL_COLLECTION_H_GENERICS
#ifndef X10_UTIL_COLLECTION_H_IMPLEMENTATION
#define X10_UTIL_COLLECTION_H_IMPLEMENTATION
#include <x10/util/Collection.h>


template<class FMGL(T)> void x10::util::Collection<FMGL(T)>::_instance_init() {
    _I_("Doing initialisation for class: x10::util::Collection<FMGL(T)>");
    
}

template<class FMGL(T)> x10aux::RuntimeType x10::util::Collection<FMGL(T)>::rtt;
template<class FMGL(T)> void x10::util::Collection<FMGL(T)>::_initRTT() {
    const x10aux::RuntimeType *canonical = x10aux::getRTT<x10::util::Collection<void> >();
    if (rtt.initStageOne(canonical)) return;
    const x10aux::RuntimeType* parents[2] = { x10aux::getRTT<x10::lang::Any>(), x10aux::getRTT<x10::util::Container<FMGL(T)> >()};
    const x10aux::RuntimeType* params[1] = { x10aux::getRTT<FMGL(T)>()};
    x10aux::RuntimeType::Variance variances[1] = { x10aux::RuntimeType::invariant};
    const char *baseName = "x10.util.Collection";
    rtt.initStageTwo(baseName, x10aux::RuntimeType::interface_kind, 2, parents, 1, params, variances);
}
#endif // X10_UTIL_COLLECTION_H_IMPLEMENTATION
#endif // __X10_UTIL_COLLECTION_H_NODEPS
