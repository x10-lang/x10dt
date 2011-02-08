#ifndef __X10_UTIL_LIST_H
#define __X10_UTIL_LIST_H

#include <x10rt.h>


#define X10_LANG_ANY_H_NODEPS
#include <x10/lang/Any.h>
#undef X10_LANG_ANY_H_NODEPS
#define X10_UTIL_COLLECTION_H_NODEPS
#include <x10/util/Collection.h>
#undef X10_UTIL_COLLECTION_H_NODEPS
#define X10_UTIL_INDEXED_H_NODEPS
#include <x10/util/Indexed.h>
#undef X10_UTIL_INDEXED_H_NODEPS
#define X10_LANG_SETTABLE_H_NODEPS
#include <x10/lang/Settable.h>
#undef X10_LANG_SETTABLE_H_NODEPS
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace util { 
template<class FMGL(T)> class ListIterator;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Comparable;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(Z2), class FMGL(U)> class Fun_0_2;
} } 
namespace x10 { namespace util { 

template<class FMGL(T)> class List;
template <> class List<void>;
template<class FMGL(T)> class List   {
    public:
    RTT_H_DECLS_INTERFACE
    
    template <class I> struct itable {
        itable(x10_boolean (I::*add) (FMGL(T)), x10_boolean (I::*addAll) (x10aux::ref<x10::util::Container<FMGL(T)> >), x10_boolean (I::*addAllWhere) (x10aux::ref<x10::util::Container<FMGL(T)> >, x10aux::ref<x10::lang::Fun_0_1<FMGL(T), x10_boolean> >), void (I::*addBefore) (x10_int, FMGL(T)), void (I::*clear) (), x10aux::ref<x10::util::Container<FMGL(T)> > (I::*clone) (), x10_boolean (I::*contains) (FMGL(T)), x10_boolean (I::*containsAll) (x10aux::ref<x10::util::Container<FMGL(T)> >), x10_boolean (I::*equals) (x10aux::ref<x10::lang::Any>), FMGL(T) (I::*getFirst) (), FMGL(T) (I::*getLast) (), x10_int (I::*hashCode) (), x10_int (I::*_m12__indexOf) (FMGL(T)), x10_int (I::*_m13__indexOf) (x10_int, FMGL(T)), x10aux::ref<x10::util::List<x10_int> > (I::*indices) (), x10_boolean (I::*isEmpty) (), x10aux::ref<x10::lang::Iterator<FMGL(T)> > (I::*iterator) (), x10aux::ref<x10::util::ListIterator<FMGL(T)> > (I::*iteratorFrom) (x10_int), x10_int (I::*_m18__lastIndexOf) (FMGL(T)), x10_int (I::*_m19__lastIndexOf) (x10_int, FMGL(T)), FMGL(T) (I::*__apply) (x10_int), FMGL(T) (I::*__set) (FMGL(T), x10_int), x10_boolean (I::*remove) (FMGL(T)), x10_boolean (I::*removeAll) (x10aux::ref<x10::util::Container<FMGL(T)> >), x10_boolean (I::*removeAllWhere) (x10aux::ref<x10::lang::Fun_0_1<FMGL(T), x10_boolean> >), FMGL(T) (I::*removeAt) (x10_int), FMGL(T) (I::*removeFirst) (), FMGL(T) (I::*removeLast) (), x10_boolean (I::*retainAll) (x10aux::ref<x10::util::Container<FMGL(T)> >), void (I::*reverse) (), x10_int (I::*size) (), void (I::*_m31__sort) (), void (I::*_m32__sort) (x10aux::ref<x10::lang::Fun_0_2<FMGL(T), FMGL(T), x10_int> >), x10aux::ref<x10::util::List<FMGL(T)> > (I::*subList) (x10_int, x10_int), x10aux::ref<x10::lang::Rail<FMGL(T) > > (I::*toRail) (), x10aux::ref<x10::lang::String> (I::*toString) (), x10aux::ref<x10::lang::String> (I::*typeName) ()) : add(add), addAll(addAll), addAllWhere(addAllWhere), addBefore(addBefore), clear(clear), clone(clone), contains(contains), containsAll(containsAll), equals(equals), getFirst(getFirst), getLast(getLast), hashCode(hashCode), _m12__indexOf(_m12__indexOf), _m13__indexOf(_m13__indexOf), indices(indices), isEmpty(isEmpty), iterator(iterator), iteratorFrom(iteratorFrom), _m18__lastIndexOf(_m18__lastIndexOf), _m19__lastIndexOf(_m19__lastIndexOf), __apply(__apply), __set(__set), remove(remove), removeAll(removeAll), removeAllWhere(removeAllWhere), removeAt(removeAt), removeFirst(removeFirst), removeLast(removeLast), retainAll(retainAll), reverse(reverse), size(size), _m31__sort(_m31__sort), _m32__sort(_m32__sort), subList(subList), toRail(toRail), toString(toString), typeName(typeName) {}
        x10_boolean (I::*add) (FMGL(T));
        x10_boolean (I::*addAll) (x10aux::ref<x10::util::Container<FMGL(T)> >);
        x10_boolean (I::*addAllWhere) (x10aux::ref<x10::util::Container<FMGL(T)> >, x10aux::ref<x10::lang::Fun_0_1<FMGL(T), x10_boolean> >);
        void (I::*addBefore) (x10_int, FMGL(T));
        void (I::*clear) ();
        x10aux::ref<x10::util::Container<FMGL(T)> > (I::*clone) ();
        x10_boolean (I::*contains) (FMGL(T));
        x10_boolean (I::*containsAll) (x10aux::ref<x10::util::Container<FMGL(T)> >);
        x10_boolean (I::*equals) (x10aux::ref<x10::lang::Any>);
        FMGL(T) (I::*getFirst) ();
        FMGL(T) (I::*getLast) ();
        x10_int (I::*hashCode) ();
        x10_int (I::*_m12__indexOf) (FMGL(T));
        x10_int (I::*_m13__indexOf) (x10_int, FMGL(T));
        x10aux::ref<x10::util::List<x10_int> > (I::*indices) ();
        x10_boolean (I::*isEmpty) ();
        x10aux::ref<x10::lang::Iterator<FMGL(T)> > (I::*iterator) ();
        x10aux::ref<x10::util::ListIterator<FMGL(T)> > (I::*iteratorFrom) (x10_int);
        x10_int (I::*_m18__lastIndexOf) (FMGL(T));
        x10_int (I::*_m19__lastIndexOf) (x10_int, FMGL(T));
        FMGL(T) (I::*__apply) (x10_int);
        FMGL(T) (I::*__set) (FMGL(T), x10_int);
        x10_boolean (I::*remove) (FMGL(T));
        x10_boolean (I::*removeAll) (x10aux::ref<x10::util::Container<FMGL(T)> >);
        x10_boolean (I::*removeAllWhere) (x10aux::ref<x10::lang::Fun_0_1<FMGL(T), x10_boolean> >);
        FMGL(T) (I::*removeAt) (x10_int);
        FMGL(T) (I::*removeFirst) ();
        FMGL(T) (I::*removeLast) ();
        x10_boolean (I::*retainAll) (x10aux::ref<x10::util::Container<FMGL(T)> >);
        void (I::*reverse) ();
        x10_int (I::*size) ();
        void (I::*_m31__sort) ();
        void (I::*_m32__sort) (x10aux::ref<x10::lang::Fun_0_2<FMGL(T), FMGL(T), x10_int> >);
        x10aux::ref<x10::util::List<FMGL(T)> > (I::*subList) (x10_int, x10_int);
        x10aux::ref<x10::lang::Rail<FMGL(T) > > (I::*toRail) ();
        x10aux::ref<x10::lang::String> (I::*toString) ();
        x10aux::ref<x10::lang::String> (I::*typeName) ();
    };
    
    template <class R> static x10_boolean add(x10aux::ref<R> _recv, FMGL(T) arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::List<FMGL(T)> >(_refRecv->_getITables())->add))(arg0);
    }
    template <class R> static x10_boolean add(R _recv, FMGL(T) arg0) {
        return R::_METHODS::add(_recv, arg0);
    }
    template <class R> static x10_boolean addAll(x10aux::ref<R> _recv, x10aux::ref<x10::util::Container<FMGL(T)> > arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::List<FMGL(T)> >(_refRecv->_getITables())->addAll))(arg0);
    }
    template <class R> static x10_boolean addAll(R _recv, x10aux::ref<x10::util::Container<FMGL(T)> > arg0) {
        return R::_METHODS::addAll(_recv, arg0);
    }
    template <class R> static x10_boolean addAllWhere(x10aux::ref<R> _recv, x10aux::ref<x10::util::Container<FMGL(T)> > arg0, x10aux::ref<x10::lang::Fun_0_1<FMGL(T), x10_boolean> > arg1) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::List<FMGL(T)> >(_refRecv->_getITables())->addAllWhere))(arg0, arg1);
    }
    template <class R> static x10_boolean addAllWhere(R _recv, x10aux::ref<x10::util::Container<FMGL(T)> > arg0, x10aux::ref<x10::lang::Fun_0_1<FMGL(T), x10_boolean> > arg1) {
        return R::_METHODS::addAllWhere(_recv, arg0, arg1);
    }
    template <class R> static void addBefore(x10aux::ref<R> _recv, x10_int arg0, FMGL(T) arg1) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        (_refRecv.operator->()->*(x10aux::findITable<x10::util::List<FMGL(T)> >(_refRecv->_getITables())->addBefore))(arg0, arg1);
    }
    template <class R> static void addBefore(R _recv, x10_int arg0, FMGL(T) arg1) {
        R::_METHODS::addBefore(_recv, arg0, arg1);
    }
    template <class R> static void clear(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        (_refRecv.operator->()->*(x10aux::findITable<x10::util::List<FMGL(T)> >(_refRecv->_getITables())->clear))();
    }
    template <class R> static void clear(R _recv) {
        R::_METHODS::clear(_recv);
    }
    template <class R> static x10aux::ref<x10::util::Collection<FMGL(T)> > clone(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::List<FMGL(T)> >(_refRecv->_getITables())->clone))();
    }
    template <class R> static x10aux::ref<x10::util::Collection<FMGL(T)> > clone(R _recv) {
        return R::_METHODS::clone(_recv);
    }
    template <class R> static x10_boolean contains(x10aux::ref<R> _recv, FMGL(T) arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::List<FMGL(T)> >(_refRecv->_getITables())->contains))(arg0);
    }
    template <class R> static x10_boolean contains(R _recv, FMGL(T) arg0) {
        return R::_METHODS::contains(_recv, arg0);
    }
    template <class R> static x10_boolean containsAll(x10aux::ref<R> _recv, x10aux::ref<x10::util::Container<FMGL(T)> > arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::List<FMGL(T)> >(_refRecv->_getITables())->containsAll))(arg0);
    }
    template <class R> static x10_boolean containsAll(R _recv, x10aux::ref<x10::util::Container<FMGL(T)> > arg0) {
        return R::_METHODS::containsAll(_recv, arg0);
    }
    template <class R> static x10_boolean equals(x10aux::ref<R> _recv, x10aux::ref<x10::lang::Any> arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::List<FMGL(T)> >(_refRecv->_getITables())->equals))(arg0);
    }
    template <class R> static x10_boolean equals(R _recv, x10aux::ref<x10::lang::Any> arg0) {
        return R::_METHODS::equals(_recv, arg0);
    }
    template <class R> static FMGL(T) getFirst(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::List<FMGL(T)> >(_refRecv->_getITables())->getFirst))();
    }
    template <class R> static FMGL(T) getFirst(R _recv) {
        return R::_METHODS::getFirst(_recv);
    }
    template <class R> static FMGL(T) getLast(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::List<FMGL(T)> >(_refRecv->_getITables())->getLast))();
    }
    template <class R> static FMGL(T) getLast(R _recv) {
        return R::_METHODS::getLast(_recv);
    }
    template <class R> static x10_int hashCode(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::List<FMGL(T)> >(_refRecv->_getITables())->hashCode))();
    }
    template <class R> static x10_int hashCode(R _recv) {
        return R::_METHODS::hashCode(_recv);
    }
    template <class R> static x10_int _m12__indexOf(x10aux::ref<R> _recv, FMGL(T) arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::List<FMGL(T)> >(_refRecv->_getITables())->_m12__indexOf))(arg0);
    }
    template <class R> static x10_int _m12__indexOf(R _recv, FMGL(T) arg0) {
        return R::_METHODS::indexOf(_recv, arg0);
    }
    template <class R> static x10_int _m13__indexOf(x10aux::ref<R> _recv, x10_int arg0, FMGL(T) arg1) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::List<FMGL(T)> >(_refRecv->_getITables())->_m13__indexOf))(arg0, arg1);
    }
    template <class R> static x10_int _m13__indexOf(R _recv, x10_int arg0, FMGL(T) arg1) {
        return R::_METHODS::indexOf(_recv, arg0, arg1);
    }
    template <class R> static x10aux::ref<x10::util::List<x10_int> > indices(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::List<FMGL(T)> >(_refRecv->_getITables())->indices))();
    }
    template <class R> static x10aux::ref<x10::util::List<x10_int> > indices(R _recv) {
        return R::_METHODS::indices(_recv);
    }
    template <class R> static x10_boolean isEmpty(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::List<FMGL(T)> >(_refRecv->_getITables())->isEmpty))();
    }
    template <class R> static x10_boolean isEmpty(R _recv) {
        return R::_METHODS::isEmpty(_recv);
    }
    template <class R> static x10aux::ref<x10::util::ListIterator<FMGL(T)> > iterator(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::List<FMGL(T)> >(_refRecv->_getITables())->iterator))();
    }
    template <class R> static x10aux::ref<x10::util::ListIterator<FMGL(T)> > iterator(R _recv) {
        return R::_METHODS::iterator(_recv);
    }
    template <class R> static x10aux::ref<x10::util::ListIterator<FMGL(T)> > iteratorFrom(x10aux::ref<R> _recv, x10_int arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::List<FMGL(T)> >(_refRecv->_getITables())->iteratorFrom))(arg0);
    }
    template <class R> static x10aux::ref<x10::util::ListIterator<FMGL(T)> > iteratorFrom(R _recv, x10_int arg0) {
        return R::_METHODS::iteratorFrom(_recv, arg0);
    }
    template <class R> static x10_int _m18__lastIndexOf(x10aux::ref<R> _recv, FMGL(T) arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::List<FMGL(T)> >(_refRecv->_getITables())->_m18__lastIndexOf))(arg0);
    }
    template <class R> static x10_int _m18__lastIndexOf(R _recv, FMGL(T) arg0) {
        return R::_METHODS::lastIndexOf(_recv, arg0);
    }
    template <class R> static x10_int _m19__lastIndexOf(x10aux::ref<R> _recv, x10_int arg0, FMGL(T) arg1) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::List<FMGL(T)> >(_refRecv->_getITables())->_m19__lastIndexOf))(arg0, arg1);
    }
    template <class R> static x10_int _m19__lastIndexOf(R _recv, x10_int arg0, FMGL(T) arg1) {
        return R::_METHODS::lastIndexOf(_recv, arg0, arg1);
    }
    template <class R> static FMGL(T) __apply(x10aux::ref<R> _recv, x10_int arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::List<FMGL(T)> >(_refRecv->_getITables())->__apply))(arg0);
    }
    template <class R> static FMGL(T) __apply(R _recv, x10_int arg0) {
        return R::_METHODS::__apply(_recv, arg0);
    }
    template <class R> static FMGL(T) __set(x10aux::ref<R> _recv, FMGL(T) arg0, x10_int arg1) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::List<FMGL(T)> >(_refRecv->_getITables())->__set))(arg0, arg1);
    }
    template <class R> static FMGL(T) __set(R _recv, FMGL(T) arg0, x10_int arg1) {
        return R::_METHODS::__set(_recv, arg0, arg1);
    }
    template <class R> static x10_boolean remove(x10aux::ref<R> _recv, FMGL(T) arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::List<FMGL(T)> >(_refRecv->_getITables())->remove))(arg0);
    }
    template <class R> static x10_boolean remove(R _recv, FMGL(T) arg0) {
        return R::_METHODS::remove(_recv, arg0);
    }
    template <class R> static x10_boolean removeAll(x10aux::ref<R> _recv, x10aux::ref<x10::util::Container<FMGL(T)> > arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::List<FMGL(T)> >(_refRecv->_getITables())->removeAll))(arg0);
    }
    template <class R> static x10_boolean removeAll(R _recv, x10aux::ref<x10::util::Container<FMGL(T)> > arg0) {
        return R::_METHODS::removeAll(_recv, arg0);
    }
    template <class R> static x10_boolean removeAllWhere(x10aux::ref<R> _recv, x10aux::ref<x10::lang::Fun_0_1<FMGL(T), x10_boolean> > arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::List<FMGL(T)> >(_refRecv->_getITables())->removeAllWhere))(arg0);
    }
    template <class R> static x10_boolean removeAllWhere(R _recv, x10aux::ref<x10::lang::Fun_0_1<FMGL(T), x10_boolean> > arg0) {
        return R::_METHODS::removeAllWhere(_recv, arg0);
    }
    template <class R> static FMGL(T) removeAt(x10aux::ref<R> _recv, x10_int arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::List<FMGL(T)> >(_refRecv->_getITables())->removeAt))(arg0);
    }
    template <class R> static FMGL(T) removeAt(R _recv, x10_int arg0) {
        return R::_METHODS::removeAt(_recv, arg0);
    }
    template <class R> static FMGL(T) removeFirst(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::List<FMGL(T)> >(_refRecv->_getITables())->removeFirst))();
    }
    template <class R> static FMGL(T) removeFirst(R _recv) {
        return R::_METHODS::removeFirst(_recv);
    }
    template <class R> static FMGL(T) removeLast(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::List<FMGL(T)> >(_refRecv->_getITables())->removeLast))();
    }
    template <class R> static FMGL(T) removeLast(R _recv) {
        return R::_METHODS::removeLast(_recv);
    }
    template <class R> static x10_boolean retainAll(x10aux::ref<R> _recv, x10aux::ref<x10::util::Container<FMGL(T)> > arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::List<FMGL(T)> >(_refRecv->_getITables())->retainAll))(arg0);
    }
    template <class R> static x10_boolean retainAll(R _recv, x10aux::ref<x10::util::Container<FMGL(T)> > arg0) {
        return R::_METHODS::retainAll(_recv, arg0);
    }
    template <class R> static void reverse(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        (_refRecv.operator->()->*(x10aux::findITable<x10::util::List<FMGL(T)> >(_refRecv->_getITables())->reverse))();
    }
    template <class R> static void reverse(R _recv) {
        R::_METHODS::reverse(_recv);
    }
    template <class R> static x10_int size(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::List<FMGL(T)> >(_refRecv->_getITables())->size))();
    }
    template <class R> static x10_int size(R _recv) {
        return R::_METHODS::size(_recv);
    }
    template <class R> static void _m31__sort(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        (_refRecv.operator->()->*(x10aux::findITable<x10::util::List<FMGL(T)> >(_refRecv->_getITables())->_m31__sort))();
    }
    template <class R> static void _m31__sort(R _recv) {
        R::_METHODS::sort(_recv);
    }
    template <class R> static void _m32__sort(x10aux::ref<R> _recv, x10aux::ref<x10::lang::Fun_0_2<FMGL(T), FMGL(T), x10_int> > arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        (_refRecv.operator->()->*(x10aux::findITable<x10::util::List<FMGL(T)> >(_refRecv->_getITables())->_m32__sort))(arg0);
    }
    template <class R> static void _m32__sort(R _recv, x10aux::ref<x10::lang::Fun_0_2<FMGL(T), FMGL(T), x10_int> > arg0) {
        R::_METHODS::sort(_recv, arg0);
    }
    template <class R> static x10aux::ref<x10::util::List<FMGL(T)> > subList(x10aux::ref<R> _recv, x10_int arg0, x10_int arg1) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::List<FMGL(T)> >(_refRecv->_getITables())->subList))(arg0, arg1);
    }
    template <class R> static x10aux::ref<x10::util::List<FMGL(T)> > subList(R _recv, x10_int arg0, x10_int arg1) {
        return R::_METHODS::subList(_recv, arg0, arg1);
    }
    template <class R> static x10aux::ref<x10::lang::Rail<FMGL(T) > > toRail(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::List<FMGL(T)> >(_refRecv->_getITables())->toRail))();
    }
    template <class R> static x10aux::ref<x10::lang::Rail<FMGL(T) > > toRail(R _recv) {
        return R::_METHODS::toRail(_recv);
    }
    template <class R> static x10aux::ref<x10::lang::String> toString(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::List<FMGL(T)> >(_refRecv->_getITables())->toString))();
    }
    template <class R> static x10aux::ref<x10::lang::String> toString(R _recv) {
        return R::_METHODS::toString(_recv);
    }
    template <class R> static x10aux::ref<x10::lang::String> typeName(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::List<FMGL(T)> >(_refRecv->_getITables())->typeName))();
    }
    template <class R> static x10aux::ref<x10::lang::String> typeName(R _recv) {
        return R::_METHODS::typeName(_recv);
    }
    void _instance_init();
    
    
};
template <> class List<void> {
    public:
    static x10aux::RuntimeType rtt;
    static const x10aux::RuntimeType* getRTT() { return & rtt; }
    
};

} } 
#endif // X10_UTIL_LIST_H

namespace x10 { namespace util { 
template<class FMGL(T)> class List;
} } 

#ifndef X10_UTIL_LIST_H_NODEPS
#define X10_UTIL_LIST_H_NODEPS
#include <x10/lang/Any.h>
#include <x10/util/Collection.h>
#include <x10/util/Indexed.h>
#include <x10/lang/Settable.h>
#include <x10/lang/Int.h>
#include <x10/util/ListIterator.h>
#include <x10/lang/Comparable.h>
#include <x10/lang/Fun_0_2.h>
#ifndef X10_UTIL_LIST_H_GENERICS
#define X10_UTIL_LIST_H_GENERICS
#endif // X10_UTIL_LIST_H_GENERICS
#ifndef X10_UTIL_LIST_H_IMPLEMENTATION
#define X10_UTIL_LIST_H_IMPLEMENTATION
#include <x10/util/List.h>


template<class FMGL(T)> void x10::util::List<FMGL(T)>::_instance_init() {
    _I_("Doing initialisation for class: x10::util::List<FMGL(T)>");
    
}

template<class FMGL(T)> x10aux::RuntimeType x10::util::List<FMGL(T)>::rtt;
template<class FMGL(T)> void x10::util::List<FMGL(T)>::_initRTT() {
    const x10aux::RuntimeType *canonical = x10aux::getRTT<x10::util::List<void> >();
    if (rtt.initStageOne(canonical)) return;
    const x10aux::RuntimeType* parents[4] = { x10aux::getRTT<x10::lang::Any>(), x10aux::getRTT<x10::util::Collection<FMGL(T)> >(), x10aux::getRTT<x10::util::Indexed<FMGL(T)> >(), x10aux::getRTT<x10::lang::Settable<x10_int, FMGL(T)> >()};
    const x10aux::RuntimeType* params[1] = { x10aux::getRTT<FMGL(T)>()};
    x10aux::RuntimeType::Variance variances[1] = { x10aux::RuntimeType::invariant};
    const char *baseName = "x10.util.List";
    rtt.initStageTwo(baseName, x10aux::RuntimeType::interface_kind, 4, parents, 1, params, variances);
}
#endif // X10_UTIL_LIST_H_IMPLEMENTATION
#endif // __X10_UTIL_LIST_H_NODEPS
