#ifndef __X10_UTIL_LISTITERATOR_H
#define __X10_UTIL_LISTITERATOR_H

#include <x10rt.h>


#define X10_LANG_ANY_H_NODEPS
#include <x10/lang/Any.h>
#undef X10_LANG_ANY_H_NODEPS
#define X10_UTIL_COLLECTIONITERATOR_H_NODEPS
#include <x10/util/CollectionIterator.h>
#undef X10_UTIL_COLLECTIONITERATOR_H_NODEPS
namespace x10 { namespace lang { 
class Boolean;
} } 
#include <x10/lang/Boolean.struct_h>
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace util { 

template<class FMGL(T)> class ListIterator;
template <> class ListIterator<void>;
template<class FMGL(T)> class ListIterator   {
    public:
    RTT_H_DECLS_INTERFACE
    
    template <class I> struct itable {
        itable(void (I::*add) (FMGL(T)), x10_boolean (I::*equals) (x10aux::ref<x10::lang::Any>), x10_boolean (I::*hasNext) (), x10_boolean (I::*hasPrevious) (), x10_int (I::*hashCode) (), FMGL(T) (I::*next) (), x10_int (I::*nextIndex) (), FMGL(T) (I::*previous) (), x10_int (I::*previousIndex) (), void (I::*remove) (), void (I::*set) (FMGL(T)), x10aux::ref<x10::lang::String> (I::*toString) (), x10aux::ref<x10::lang::String> (I::*typeName) ()) : add(add), equals(equals), hasNext(hasNext), hasPrevious(hasPrevious), hashCode(hashCode), next(next), nextIndex(nextIndex), previous(previous), previousIndex(previousIndex), remove(remove), set(set), toString(toString), typeName(typeName) {}
        void (I::*add) (FMGL(T));
        x10_boolean (I::*equals) (x10aux::ref<x10::lang::Any>);
        x10_boolean (I::*hasNext) ();
        x10_boolean (I::*hasPrevious) ();
        x10_int (I::*hashCode) ();
        FMGL(T) (I::*next) ();
        x10_int (I::*nextIndex) ();
        FMGL(T) (I::*previous) ();
        x10_int (I::*previousIndex) ();
        void (I::*remove) ();
        void (I::*set) (FMGL(T));
        x10aux::ref<x10::lang::String> (I::*toString) ();
        x10aux::ref<x10::lang::String> (I::*typeName) ();
    };
    
    template <class R> static void add(x10aux::ref<R> _recv, FMGL(T) arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        (_refRecv.operator->()->*(x10aux::findITable<x10::util::ListIterator<FMGL(T)> >(_refRecv->_getITables())->add))(arg0);
    }
    template <class R> static void add(R _recv, FMGL(T) arg0) {
        R::_METHODS::add(_recv, arg0);
    }
    template <class R> static x10_boolean equals(x10aux::ref<R> _recv, x10aux::ref<x10::lang::Any> arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::ListIterator<FMGL(T)> >(_refRecv->_getITables())->equals))(arg0);
    }
    template <class R> static x10_boolean equals(R _recv, x10aux::ref<x10::lang::Any> arg0) {
        return R::_METHODS::equals(_recv, arg0);
    }
    template <class R> static x10_boolean hasNext(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::ListIterator<FMGL(T)> >(_refRecv->_getITables())->hasNext))();
    }
    template <class R> static x10_boolean hasNext(R _recv) {
        return R::_METHODS::hasNext(_recv);
    }
    template <class R> static x10_boolean hasPrevious(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::ListIterator<FMGL(T)> >(_refRecv->_getITables())->hasPrevious))();
    }
    template <class R> static x10_boolean hasPrevious(R _recv) {
        return R::_METHODS::hasPrevious(_recv);
    }
    template <class R> static x10_int hashCode(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::ListIterator<FMGL(T)> >(_refRecv->_getITables())->hashCode))();
    }
    template <class R> static x10_int hashCode(R _recv) {
        return R::_METHODS::hashCode(_recv);
    }
    template <class R> static FMGL(T) next(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::ListIterator<FMGL(T)> >(_refRecv->_getITables())->next))();
    }
    template <class R> static FMGL(T) next(R _recv) {
        return R::_METHODS::next(_recv);
    }
    template <class R> static x10_int nextIndex(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::ListIterator<FMGL(T)> >(_refRecv->_getITables())->nextIndex))();
    }
    template <class R> static x10_int nextIndex(R _recv) {
        return R::_METHODS::nextIndex(_recv);
    }
    template <class R> static FMGL(T) previous(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::ListIterator<FMGL(T)> >(_refRecv->_getITables())->previous))();
    }
    template <class R> static FMGL(T) previous(R _recv) {
        return R::_METHODS::previous(_recv);
    }
    template <class R> static x10_int previousIndex(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::ListIterator<FMGL(T)> >(_refRecv->_getITables())->previousIndex))();
    }
    template <class R> static x10_int previousIndex(R _recv) {
        return R::_METHODS::previousIndex(_recv);
    }
    template <class R> static void remove(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        (_refRecv.operator->()->*(x10aux::findITable<x10::util::ListIterator<FMGL(T)> >(_refRecv->_getITables())->remove))();
    }
    template <class R> static void remove(R _recv) {
        R::_METHODS::remove(_recv);
    }
    template <class R> static void set(x10aux::ref<R> _recv, FMGL(T) arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        (_refRecv.operator->()->*(x10aux::findITable<x10::util::ListIterator<FMGL(T)> >(_refRecv->_getITables())->set))(arg0);
    }
    template <class R> static void set(R _recv, FMGL(T) arg0) {
        R::_METHODS::set(_recv, arg0);
    }
    template <class R> static x10aux::ref<x10::lang::String> toString(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::ListIterator<FMGL(T)> >(_refRecv->_getITables())->toString))();
    }
    template <class R> static x10aux::ref<x10::lang::String> toString(R _recv) {
        return R::_METHODS::toString(_recv);
    }
    template <class R> static x10aux::ref<x10::lang::String> typeName(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::ListIterator<FMGL(T)> >(_refRecv->_getITables())->typeName))();
    }
    template <class R> static x10aux::ref<x10::lang::String> typeName(R _recv) {
        return R::_METHODS::typeName(_recv);
    }
    void _instance_init();
    
    
};
template <> class ListIterator<void> {
    public:
    static x10aux::RuntimeType rtt;
    static const x10aux::RuntimeType* getRTT() { return & rtt; }
    
};

} } 
#endif // X10_UTIL_LISTITERATOR_H

namespace x10 { namespace util { 
template<class FMGL(T)> class ListIterator;
} } 

#ifndef X10_UTIL_LISTITERATOR_H_NODEPS
#define X10_UTIL_LISTITERATOR_H_NODEPS
#include <x10/lang/Any.h>
#include <x10/util/CollectionIterator.h>
#include <x10/lang/Boolean.h>
#include <x10/lang/Int.h>
#ifndef X10_UTIL_LISTITERATOR_H_GENERICS
#define X10_UTIL_LISTITERATOR_H_GENERICS
#endif // X10_UTIL_LISTITERATOR_H_GENERICS
#ifndef X10_UTIL_LISTITERATOR_H_IMPLEMENTATION
#define X10_UTIL_LISTITERATOR_H_IMPLEMENTATION
#include <x10/util/ListIterator.h>


template<class FMGL(T)> void x10::util::ListIterator<FMGL(T)>::_instance_init() {
    _I_("Doing initialisation for class: x10::util::ListIterator<FMGL(T)>");
    
}

template<class FMGL(T)> x10aux::RuntimeType x10::util::ListIterator<FMGL(T)>::rtt;
template<class FMGL(T)> void x10::util::ListIterator<FMGL(T)>::_initRTT() {
    const x10aux::RuntimeType *canonical = x10aux::getRTT<x10::util::ListIterator<void> >();
    if (rtt.initStageOne(canonical)) return;
    const x10aux::RuntimeType* parents[2] = { x10aux::getRTT<x10::lang::Any>(), x10aux::getRTT<x10::util::CollectionIterator<FMGL(T)> >()};
    const x10aux::RuntimeType* params[1] = { x10aux::getRTT<FMGL(T)>()};
    x10aux::RuntimeType::Variance variances[1] = { x10aux::RuntimeType::invariant};
    const char *baseName = "x10.util.ListIterator";
    rtt.initStageTwo(baseName, x10aux::RuntimeType::interface_kind, 2, parents, 1, params, variances);
}
#endif // X10_UTIL_LISTITERATOR_H_IMPLEMENTATION
#endif // __X10_UTIL_LISTITERATOR_H_NODEPS
