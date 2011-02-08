#ifndef __X10_UTIL_HASHSET_H
#define __X10_UTIL_HASHSET_H

#include <x10rt.h>


#define X10_UTIL_MAPSET_H_NODEPS
#include <x10/util/MapSet.h>
#undef X10_UTIL_MAPSET_H_NODEPS
#define X10_IO_CUSTOMSERIALIZATION_H_NODEPS
#include <x10/io/CustomSerialization.h>
#undef X10_IO_CUSTOMSERIALIZATION_H_NODEPS
namespace x10 { namespace util { 
template<class FMGL(K), class FMGL(V)> class Map;
} } 
namespace x10 { namespace lang { 
class Boolean;
} } 
#include <x10/lang/Boolean.struct_h>
namespace x10 { namespace util { 
template<class FMGL(K), class FMGL(V)> class HashMap;
} } 
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace io { 
class SerialData;
} } 
namespace x10 { namespace util { 
template<class FMGL(T)> class Collection;
} } 
namespace x10 { namespace util { 

template<class FMGL(T)> class HashSet;
template <> class HashSet<void>;
template<class FMGL(T)> class HashSet : public x10::util::MapSet<FMGL(T)>
  {
    public:
    RTT_H_DECLS_CLASS
    
    static x10aux::itable_entry _itables[7];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    static typename x10::util::Container<FMGL(T)>::template itable<x10::util::HashSet<FMGL(T)> > _itable_0;
    
    static x10::lang::Any::itable<x10::util::HashSet<FMGL(T)> > _itable_1;
    
    static typename x10::lang::Iterable<FMGL(T)>::template itable<x10::util::HashSet<FMGL(T)> > _itable_2;
    
    static typename x10::util::Collection<FMGL(T)>::template itable<x10::util::HashSet<FMGL(T)> > _itable_3;
    
    static typename x10::util::Set<FMGL(T)>::template itable<x10::util::HashSet<FMGL(T)> > _itable_4;
    
    static x10::io::CustomSerialization::itable<x10::util::HashSet<FMGL(T)> > _itable_5;
    
    void _instance_init();
    
    void _constructor();
    
    static x10aux::ref<x10::util::HashSet<FMGL(T)> > _make();
    
    void _constructor(x10_int sz);
    
    static x10aux::ref<x10::util::HashSet<FMGL(T)> > _make(x10_int sz);
    
    virtual x10aux::ref<x10::io::SerialData> serialize();
    void _constructor(x10aux::ref<x10::io::SerialData> a);
    
    static x10aux::ref<x10::util::HashSet<FMGL(T)> > _make(x10aux::ref<x10::io::SerialData> a);
    
    virtual x10aux::ref<x10::util::Container<FMGL(T)> > clone();
    virtual x10aux::ref<x10::util::HashSet<FMGL(T)> > x10__util__HashSet____x10__util__HashSet__this(
      );
    
    // Serialization
    public: static const x10aux::serialization_id_t _serialization_id;
    
    public: virtual x10aux::serialization_id_t _get_serialization_id() {
         return _serialization_id;
    }
    
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: template<class __T> static x10aux::ref<__T> _deserializer(x10aux::deserialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};
template <> class HashSet<void> : public x10::util::MapSet<void> {
    public:
    static x10aux::RuntimeType rtt;
    static const x10aux::RuntimeType* getRTT() { return & rtt; }
    
};

} } 
#endif // X10_UTIL_HASHSET_H

namespace x10 { namespace util { 
template<class FMGL(T)> class HashSet;
} } 

#ifndef X10_UTIL_HASHSET_H_NODEPS
#define X10_UTIL_HASHSET_H_NODEPS
#include <x10/util/MapSet.h>
#include <x10/io/CustomSerialization.h>
#include <x10/util/Map.h>
#include <x10/lang/Boolean.h>
#include <x10/util/HashMap.h>
#include <x10/lang/Int.h>
#include <x10/io/SerialData.h>
#include <x10/util/Collection.h>
#ifndef X10_UTIL_HASHSET_H_GENERICS
#define X10_UTIL_HASHSET_H_GENERICS
template<class FMGL(T)> template<class __T> x10aux::ref<__T> x10::util::HashSet<FMGL(T)>::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::util::HashSet<FMGL(T)> > this_ = new (memset(x10aux::alloc<x10::util::HashSet<FMGL(T)> >(), 0, sizeof(x10::util::HashSet<FMGL(T)>))) x10::util::HashSet<FMGL(T)>();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_UTIL_HASHSET_H_GENERICS
#ifndef X10_UTIL_HASHSET_H_IMPLEMENTATION
#define X10_UTIL_HASHSET_H_IMPLEMENTATION
#include <x10/util/HashSet.h>


template<class FMGL(T)> typename x10::util::Container<FMGL(T)>::template itable<x10::util::HashSet<FMGL(T)> >  x10::util::HashSet<FMGL(T)>::_itable_0(&x10::util::HashSet<FMGL(T)>::clone, &x10::util::HashSet<FMGL(T)>::contains, &x10::util::HashSet<FMGL(T)>::containsAll, &x10::util::HashSet<FMGL(T)>::equals, &x10::util::HashSet<FMGL(T)>::hashCode, &x10::util::HashSet<FMGL(T)>::isEmpty, &x10::util::HashSet<FMGL(T)>::iterator, &x10::util::HashSet<FMGL(T)>::size, &x10::util::HashSet<FMGL(T)>::toRail, &x10::util::HashSet<FMGL(T)>::toString, &x10::util::HashSet<FMGL(T)>::typeName);
template<class FMGL(T)> x10::lang::Any::itable<x10::util::HashSet<FMGL(T)> >  x10::util::HashSet<FMGL(T)>::_itable_1(&x10::util::HashSet<FMGL(T)>::equals, &x10::util::HashSet<FMGL(T)>::hashCode, &x10::util::HashSet<FMGL(T)>::toString, &x10::util::HashSet<FMGL(T)>::typeName);
template<class FMGL(T)> typename x10::lang::Iterable<FMGL(T)>::template itable<x10::util::HashSet<FMGL(T)> >  x10::util::HashSet<FMGL(T)>::_itable_2(&x10::util::HashSet<FMGL(T)>::equals, &x10::util::HashSet<FMGL(T)>::hashCode, &x10::util::HashSet<FMGL(T)>::iterator, &x10::util::HashSet<FMGL(T)>::toString, &x10::util::HashSet<FMGL(T)>::typeName);
template<class FMGL(T)> typename x10::util::Collection<FMGL(T)>::template itable<x10::util::HashSet<FMGL(T)> >  x10::util::HashSet<FMGL(T)>::_itable_3(&x10::util::HashSet<FMGL(T)>::add, &x10::util::HashSet<FMGL(T)>::addAll, &x10::util::HashSet<FMGL(T)>::addAllWhere, &x10::util::HashSet<FMGL(T)>::clear, &x10::util::HashSet<FMGL(T)>::clone, &x10::util::HashSet<FMGL(T)>::contains, &x10::util::HashSet<FMGL(T)>::containsAll, &x10::util::HashSet<FMGL(T)>::equals, &x10::util::HashSet<FMGL(T)>::hashCode, &x10::util::HashSet<FMGL(T)>::isEmpty, &x10::util::HashSet<FMGL(T)>::iterator, &x10::util::HashSet<FMGL(T)>::remove, &x10::util::HashSet<FMGL(T)>::removeAll, &x10::util::HashSet<FMGL(T)>::removeAllWhere, &x10::util::HashSet<FMGL(T)>::retainAll, &x10::util::HashSet<FMGL(T)>::size, &x10::util::HashSet<FMGL(T)>::toRail, &x10::util::HashSet<FMGL(T)>::toString, &x10::util::HashSet<FMGL(T)>::typeName);
template<class FMGL(T)> typename x10::util::Set<FMGL(T)>::template itable<x10::util::HashSet<FMGL(T)> >  x10::util::HashSet<FMGL(T)>::_itable_4(&x10::util::HashSet<FMGL(T)>::add, &x10::util::HashSet<FMGL(T)>::addAll, &x10::util::HashSet<FMGL(T)>::addAllWhere, &x10::util::HashSet<FMGL(T)>::clear, &x10::util::HashSet<FMGL(T)>::clone, &x10::util::HashSet<FMGL(T)>::contains, &x10::util::HashSet<FMGL(T)>::containsAll, &x10::util::HashSet<FMGL(T)>::equals, &x10::util::HashSet<FMGL(T)>::hashCode, &x10::util::HashSet<FMGL(T)>::isEmpty, &x10::util::HashSet<FMGL(T)>::iterator, &x10::util::HashSet<FMGL(T)>::remove, &x10::util::HashSet<FMGL(T)>::removeAll, &x10::util::HashSet<FMGL(T)>::removeAllWhere, &x10::util::HashSet<FMGL(T)>::retainAll, &x10::util::HashSet<FMGL(T)>::size, &x10::util::HashSet<FMGL(T)>::toRail, &x10::util::HashSet<FMGL(T)>::toString, &x10::util::HashSet<FMGL(T)>::typeName);
template<class FMGL(T)> x10::io::CustomSerialization::itable<x10::util::HashSet<FMGL(T)> >  x10::util::HashSet<FMGL(T)>::_itable_5(&x10::util::HashSet<FMGL(T)>::equals, &x10::util::HashSet<FMGL(T)>::hashCode, &x10::util::HashSet<FMGL(T)>::serialize, &x10::util::HashSet<FMGL(T)>::toString, &x10::util::HashSet<FMGL(T)>::typeName);
template<class FMGL(T)> x10aux::itable_entry x10::util::HashSet<FMGL(T)>::_itables[7] = {x10aux::itable_entry(&x10aux::getRTT<x10::util::Container<FMGL(T)> >, &_itable_0), x10aux::itable_entry(&x10aux::getRTT<x10::lang::Any>, &_itable_1), x10aux::itable_entry(&x10aux::getRTT<x10::lang::Iterable<FMGL(T)> >, &_itable_2), x10aux::itable_entry(&x10aux::getRTT<x10::util::Collection<FMGL(T)> >, &_itable_3), x10aux::itable_entry(&x10aux::getRTT<x10::util::Set<FMGL(T)> >, &_itable_4), x10aux::itable_entry(&x10aux::getRTT<x10::io::CustomSerialization>, &_itable_5), x10aux::itable_entry(NULL, (void*)x10aux::getRTT<x10::util::HashSet<FMGL(T)> >())};
template<class FMGL(T)> void x10::util::HashSet<FMGL(T)>::_instance_init() {
    _I_("Doing initialisation for class: x10::util::HashSet<FMGL(T)>");
    
}


//#line 16 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashSet.x10": x10.ast.X10ConstructorDecl_c
template<class FMGL(T)> void x10::util::HashSet<FMGL(T)>::_constructor() {
    this->::x10::util::MapSet<FMGL(T)>::_constructor(x10aux::class_cast_unchecked<x10aux::ref<x10::util::Map<FMGL(T), x10_boolean> > >(x10::util::HashMap<FMGL(T), x10_boolean>::_make()));
    {
     
    }
    
}
template<class FMGL(T)> x10aux::ref<x10::util::HashSet<FMGL(T)> > x10::util::HashSet<FMGL(T)>::_make(
                          ) {
    x10aux::ref<x10::util::HashSet<FMGL(T)> > this_ = new (memset(x10aux::alloc<x10::util::HashSet<FMGL(T)> >(), 0, sizeof(x10::util::HashSet<FMGL(T)>))) x10::util::HashSet<FMGL(T)>();
    this_->_constructor();
    return this_;
}



//#line 17 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashSet.x10": x10.ast.X10ConstructorDecl_c
template<class FMGL(T)> void x10::util::HashSet<FMGL(T)>::_constructor(
                          x10_int sz) {
    this->::x10::util::MapSet<FMGL(T)>::_constructor(x10aux::class_cast_unchecked<x10aux::ref<x10::util::Map<FMGL(T), x10_boolean> > >(x10::util::HashMap<FMGL(T), x10_boolean>::_make(sz)));
    {
     
    }
    
}
template<class FMGL(T)> x10aux::ref<x10::util::HashSet<FMGL(T)> > x10::util::HashSet<FMGL(T)>::_make(
                          x10_int sz) {
    x10aux::ref<x10::util::HashSet<FMGL(T)> > this_ = new (memset(x10aux::alloc<x10::util::HashSet<FMGL(T)> >(), 0, sizeof(x10::util::HashSet<FMGL(T)>))) x10::util::HashSet<FMGL(T)>();
    this_->_constructor(sz);
    return this_;
}



//#line 18 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashSet.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10aux::ref<x10::io::SerialData> x10::util::HashSet<FMGL(T)>::serialize(
  ) {
    
    //#line 18 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashSet.x10": x10.ast.X10Return_c
    return x10aux::nullCheck((x10aux::class_cast<x10aux::ref<x10::util::HashMap<FMGL(T), x10_boolean> > >(((x10aux::ref<x10::util::HashSet<FMGL(T)> >)this)->
                                                                                                            FMGL(map))))->serialize();
    
}

//#line 19 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashSet.x10": x10.ast.X10ConstructorDecl_c
template<class FMGL(T)> void x10::util::HashSet<FMGL(T)>::_constructor(
                          x10aux::ref<x10::io::SerialData> a)
{
    this->::x10::util::MapSet<FMGL(T)>::_constructor(
      x10aux::class_cast_unchecked<x10aux::ref<x10::util::Map<FMGL(T), x10_boolean> > >(x10::util::HashMap<FMGL(T), x10_boolean>::_make(a)));
    {
     
    }
    
}
template<class FMGL(T)>
x10aux::ref<x10::util::HashSet<FMGL(T)> > x10::util::HashSet<FMGL(T)>::_make(
  x10aux::ref<x10::io::SerialData> a)
{
    x10aux::ref<x10::util::HashSet<FMGL(T)> > this_ = new (memset(x10aux::alloc<x10::util::HashSet<FMGL(T)> >(), 0, sizeof(x10::util::HashSet<FMGL(T)>))) x10::util::HashSet<FMGL(T)>();
    this_->_constructor(a);
    return this_;
}



//#line 20 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashSet.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10aux::ref<x10::util::Container<FMGL(T)> >
  x10::util::HashSet<FMGL(T)>::clone(
  ) {
    
    //#line 20 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashSet.x10": x10.ast.X10Return_c
    return x10::util::HashSet<FMGL(T)>::_make(((x10aux::ref<x10::util::HashSet<FMGL(T)> >)this)->serialize());
    
}

//#line 15 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashSet.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10aux::ref<x10::util::HashSet<FMGL(T)> >
  x10::util::HashSet<FMGL(T)>::x10__util__HashSet____x10__util__HashSet__this(
  ) {
    
    //#line 15 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashSet.x10": x10.ast.X10Return_c
    return ((x10aux::ref<x10::util::HashSet<FMGL(T)> >)this);
    
}
template<class FMGL(T)> const x10aux::serialization_id_t x10::util::HashSet<FMGL(T)>::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(x10::util::HashSet<FMGL(T)>::template _deserializer<x10::lang::Reference>, x10aux::CLOSURE_KIND_NOT_ASYNC);

template<class FMGL(T)> void x10::util::HashSet<FMGL(T)>::_serialize_body(x10aux::serialization_buffer& buf) {
    /* NOTE: Implements x10.io.CustomSerialization */
    buf.write(this->serialize());
    
}

template<class FMGL(T)> void x10::util::HashSet<FMGL(T)>::_deserialize_body(x10aux::deserialization_buffer& buf) {
    /* NOTE: Implements x10.io.CustomSerialization */
    x10aux::ref<x10::io::SerialData>val_ = buf.read<x10aux::ref<x10::io::SerialData> >();
    _constructor(val_);
    
}

template<class FMGL(T)> x10aux::RuntimeType x10::util::HashSet<FMGL(T)>::rtt;
template<class FMGL(T)> void x10::util::HashSet<FMGL(T)>::_initRTT() {
    const x10aux::RuntimeType *canonical = x10aux::getRTT<x10::util::HashSet<void> >();
    if (rtt.initStageOne(canonical)) return;
    const x10aux::RuntimeType* parents[2] = { x10aux::getRTT<x10::util::MapSet<FMGL(T)> >(), x10aux::getRTT<x10::io::CustomSerialization>()};
    const x10aux::RuntimeType* params[1] = { x10aux::getRTT<FMGL(T)>()};
    x10aux::RuntimeType::Variance variances[1] = { x10aux::RuntimeType::invariant};
    const char *baseName = "x10.util.HashSet";
    rtt.initStageTwo(baseName, x10aux::RuntimeType::class_kind, 2, parents, 1, params, variances);
}
#endif // X10_UTIL_HASHSET_H_IMPLEMENTATION
#endif // __X10_UTIL_HASHSET_H_NODEPS
