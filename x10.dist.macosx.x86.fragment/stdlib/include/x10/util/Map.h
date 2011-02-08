#ifndef __X10_UTIL_MAP_H
#define __X10_UTIL_MAP_H

#include <x10rt.h>


#define X10_LANG_ANY_H_NODEPS
#include <x10/lang/Any.h>
#undef X10_LANG_ANY_H_NODEPS
namespace x10 { namespace lang { 
class Boolean;
} } 
#include <x10/lang/Boolean.struct_h>
namespace x10 { namespace util { 
template<class FMGL(T)> class Box;
} } 
namespace x10 { namespace util { 
template<class FMGL(T)> class Set;
} } 
namespace x10 { namespace util { 
template<class FMGL(T)> class Set;
} } 
namespace x10 { namespace util { 
template<class FMGL(Key), class FMGL(Val)> class Map__Entry;
} } 
namespace x10 { namespace util { 

template<class FMGL(K), class FMGL(V)> class Map;
template <> class Map<void, void>;
template<class FMGL(K), class FMGL(V)> class Map   {
    public:
    RTT_H_DECLS_INTERFACE
    
    template <class I> struct itable {
        itable(void (I::*clear) (), x10_boolean (I::*containsKey) (FMGL(K)), x10aux::ref<x10::util::Set<x10aux::ref<x10::util::Map__Entry<FMGL(K), FMGL(V)> > > > (I::*entries) (), x10_boolean (I::*equals) (x10aux::ref<x10::lang::Any>), x10aux::ref<x10::util::Box<FMGL(V)> > (I::*get) (FMGL(K)), FMGL(V) (I::*getOrElse) (FMGL(K), FMGL(V)), FMGL(V) (I::*getOrThrow) (FMGL(K)), x10_int (I::*hashCode) (), x10aux::ref<x10::util::Set<FMGL(K)> > (I::*keySet) (), x10aux::ref<x10::util::Box<FMGL(V)> > (I::*put) (FMGL(K), FMGL(V)), x10aux::ref<x10::util::Box<FMGL(V)> > (I::*remove) (FMGL(K)), x10aux::ref<x10::lang::String> (I::*toString) (), x10aux::ref<x10::lang::String> (I::*typeName) ()) : clear(clear), containsKey(containsKey), entries(entries), equals(equals), get(get), getOrElse(getOrElse), getOrThrow(getOrThrow), hashCode(hashCode), keySet(keySet), put(put), remove(remove), toString(toString), typeName(typeName) {}
        void (I::*clear) ();
        x10_boolean (I::*containsKey) (FMGL(K));
        x10aux::ref<x10::util::Set<x10aux::ref<x10::util::Map__Entry<FMGL(K), FMGL(V)> > > > (I::*entries) ();
        x10_boolean (I::*equals) (x10aux::ref<x10::lang::Any>);
        x10aux::ref<x10::util::Box<FMGL(V)> > (I::*get) (FMGL(K));
        FMGL(V) (I::*getOrElse) (FMGL(K), FMGL(V));
        FMGL(V) (I::*getOrThrow) (FMGL(K));
        x10_int (I::*hashCode) ();
        x10aux::ref<x10::util::Set<FMGL(K)> > (I::*keySet) ();
        x10aux::ref<x10::util::Box<FMGL(V)> > (I::*put) (FMGL(K), FMGL(V));
        x10aux::ref<x10::util::Box<FMGL(V)> > (I::*remove) (FMGL(K));
        x10aux::ref<x10::lang::String> (I::*toString) ();
        x10aux::ref<x10::lang::String> (I::*typeName) ();
    };
    
    template <class R> static void clear(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        (_refRecv.operator->()->*(x10aux::findITable<x10::util::Map<FMGL(K), FMGL(V)> >(_refRecv->_getITables())->clear))();
    }
    template <class R> static void clear(R _recv) {
        R::_METHODS::clear(_recv);
    }
    template <class R> static x10_boolean containsKey(x10aux::ref<R> _recv, FMGL(K) arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::Map<FMGL(K), FMGL(V)> >(_refRecv->_getITables())->containsKey))(arg0);
    }
    template <class R> static x10_boolean containsKey(R _recv, FMGL(K) arg0) {
        return R::_METHODS::containsKey(_recv, arg0);
    }
    template <class R> static x10aux::ref<x10::util::Set<x10aux::ref<x10::util::Map__Entry<FMGL(K), FMGL(V)> > > > entries(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::Map<FMGL(K), FMGL(V)> >(_refRecv->_getITables())->entries))();
    }
    template <class R> static x10aux::ref<x10::util::Set<x10aux::ref<x10::util::Map__Entry<FMGL(K), FMGL(V)> > > > entries(R _recv) {
        return R::_METHODS::entries(_recv);
    }
    template <class R> static x10_boolean equals(x10aux::ref<R> _recv, x10aux::ref<x10::lang::Any> arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::Map<FMGL(K), FMGL(V)> >(_refRecv->_getITables())->equals))(arg0);
    }
    template <class R> static x10_boolean equals(R _recv, x10aux::ref<x10::lang::Any> arg0) {
        return R::_METHODS::equals(_recv, arg0);
    }
    template <class R> static x10aux::ref<x10::util::Box<FMGL(V)> > get(x10aux::ref<R> _recv, FMGL(K) arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::Map<FMGL(K), FMGL(V)> >(_refRecv->_getITables())->get))(arg0);
    }
    template <class R> static x10aux::ref<x10::util::Box<FMGL(V)> > get(R _recv, FMGL(K) arg0) {
        return R::_METHODS::get(_recv, arg0);
    }
    template <class R> static FMGL(V) getOrElse(x10aux::ref<R> _recv, FMGL(K) arg0, FMGL(V) arg1) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::Map<FMGL(K), FMGL(V)> >(_refRecv->_getITables())->getOrElse))(arg0, arg1);
    }
    template <class R> static FMGL(V) getOrElse(R _recv, FMGL(K) arg0, FMGL(V) arg1) {
        return R::_METHODS::getOrElse(_recv, arg0, arg1);
    }
    template <class R> static FMGL(V) getOrThrow(x10aux::ref<R> _recv, FMGL(K) arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::Map<FMGL(K), FMGL(V)> >(_refRecv->_getITables())->getOrThrow))(arg0);
    }
    template <class R> static FMGL(V) getOrThrow(R _recv, FMGL(K) arg0) {
        return R::_METHODS::getOrThrow(_recv, arg0);
    }
    template <class R> static x10_int hashCode(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::Map<FMGL(K), FMGL(V)> >(_refRecv->_getITables())->hashCode))();
    }
    template <class R> static x10_int hashCode(R _recv) {
        return R::_METHODS::hashCode(_recv);
    }
    template <class R> static x10aux::ref<x10::util::Set<FMGL(K)> > keySet(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::Map<FMGL(K), FMGL(V)> >(_refRecv->_getITables())->keySet))();
    }
    template <class R> static x10aux::ref<x10::util::Set<FMGL(K)> > keySet(R _recv) {
        return R::_METHODS::keySet(_recv);
    }
    template <class R> static x10aux::ref<x10::util::Box<FMGL(V)> > put(x10aux::ref<R> _recv, FMGL(K) arg0, FMGL(V) arg1) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::Map<FMGL(K), FMGL(V)> >(_refRecv->_getITables())->put))(arg0, arg1);
    }
    template <class R> static x10aux::ref<x10::util::Box<FMGL(V)> > put(R _recv, FMGL(K) arg0, FMGL(V) arg1) {
        return R::_METHODS::put(_recv, arg0, arg1);
    }
    template <class R> static x10aux::ref<x10::util::Box<FMGL(V)> > remove(x10aux::ref<R> _recv, FMGL(K) arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::Map<FMGL(K), FMGL(V)> >(_refRecv->_getITables())->remove))(arg0);
    }
    template <class R> static x10aux::ref<x10::util::Box<FMGL(V)> > remove(R _recv, FMGL(K) arg0) {
        return R::_METHODS::remove(_recv, arg0);
    }
    template <class R> static x10aux::ref<x10::lang::String> toString(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::Map<FMGL(K), FMGL(V)> >(_refRecv->_getITables())->toString))();
    }
    template <class R> static x10aux::ref<x10::lang::String> toString(R _recv) {
        return R::_METHODS::toString(_recv);
    }
    template <class R> static x10aux::ref<x10::lang::String> typeName(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::util::Map<FMGL(K), FMGL(V)> >(_refRecv->_getITables())->typeName))();
    }
    template <class R> static x10aux::ref<x10::lang::String> typeName(R _recv) {
        return R::_METHODS::typeName(_recv);
    }
    void _instance_init();
    
    
};
template <> class Map<void, void> {
    public:
    static x10aux::RuntimeType rtt;
    static const x10aux::RuntimeType* getRTT() { return & rtt; }
    
};

} } 
#endif // X10_UTIL_MAP_H

namespace x10 { namespace util { 
template<class FMGL(K), class FMGL(V)> class Map;
} } 

#ifndef X10_UTIL_MAP_H_NODEPS
#define X10_UTIL_MAP_H_NODEPS
#include <x10/lang/Any.h>
#include <x10/lang/Boolean.h>
#include <x10/util/Box.h>
#include <x10/util/Set.h>
#include <x10/util/Set.h>
#include <x10/util/Map__Entry.h>
#ifndef X10_UTIL_MAP_H_GENERICS
#define X10_UTIL_MAP_H_GENERICS
#endif // X10_UTIL_MAP_H_GENERICS
#ifndef X10_UTIL_MAP_H_IMPLEMENTATION
#define X10_UTIL_MAP_H_IMPLEMENTATION
#include <x10/util/Map.h>


template<class FMGL(K), class FMGL(V)> void x10::util::Map<FMGL(K), FMGL(V)>::_instance_init() {
    _I_("Doing initialisation for class: x10::util::Map<FMGL(K), FMGL(V)>");
    
}

template<class FMGL(K), class FMGL(V)> x10aux::RuntimeType x10::util::Map<FMGL(K), FMGL(V)>::rtt;
template<class FMGL(K), class FMGL(V)> void x10::util::Map<FMGL(K), FMGL(V)>::_initRTT() {
    const x10aux::RuntimeType *canonical = x10aux::getRTT<x10::util::Map<void, void> >();
    if (rtt.initStageOne(canonical)) return;
    const x10aux::RuntimeType* parents[1] = { x10aux::getRTT<x10::lang::Any>()};
    const x10aux::RuntimeType* params[2] = { x10aux::getRTT<FMGL(K)>(), x10aux::getRTT<FMGL(V)>()};
    x10aux::RuntimeType::Variance variances[2] = { x10aux::RuntimeType::invariant, x10aux::RuntimeType::invariant};
    const char *baseName = "x10.util.Map";
    rtt.initStageTwo(baseName, x10aux::RuntimeType::interface_kind, 1, parents, 2, params, variances);
}
#endif // X10_UTIL_MAP_H_IMPLEMENTATION
#endif // __X10_UTIL_MAP_H_NODEPS
