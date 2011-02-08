#ifndef __X10_UTIL_HASHMAP_H
#define __X10_UTIL_HASHMAP_H

#include <x10rt.h>


#define X10_LANG_OBJECT_H_NODEPS
#include <x10/lang/Object.h>
#undef X10_LANG_OBJECT_H_NODEPS
#define X10_UTIL_MAP_H_NODEPS
#include <x10/util/Map.h>
#undef X10_UTIL_MAP_H_NODEPS
#define X10_IO_CUSTOMSERIALIZATION_H_NODEPS
#include <x10/io/CustomSerialization.h>
#undef X10_IO_CUSTOMSERIALIZATION_H_NODEPS
#define X10_LANG_INT_STRUCT_H_NODEPS
#include <x10/lang/Int.struct_h>
#undef X10_LANG_INT_STRUCT_H_NODEPS
#define X10_LANG_BOOLEAN_STRUCT_H_NODEPS
#include <x10/lang/Boolean.struct_h>
#undef X10_LANG_BOOLEAN_STRUCT_H_NODEPS
namespace x10 { namespace lang { 
template<class FMGL(T)> class Rail;
} } 
namespace x10 { namespace util { 
template<class FMGL(Key), class FMGL(Value)> class HashMap__HashEntry;
} } 
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace lang { 
class Boolean;
} } 
#include <x10/lang/Boolean.struct_h>
namespace x10 { namespace lang { 
template<class FMGL(T)> class Rail;
} } 
namespace x10 { namespace compiler { 
class NonEscaping;
} } 
namespace x10 { namespace compiler { 
class TempNoInline_1;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(Z2), class FMGL(U)> class Fun_0_2;
} } 
namespace x10 { namespace util { 
template<class FMGL(T)> class Box;
} } 
namespace x10 { namespace util { 
template<class FMGL(Key), class FMGL(Value)> class HashMap__HashEntry;
} } 
namespace x10 { namespace util { 
template<class FMGL(Key), class FMGL(Value)> class HashMap__HashEntry;
} } 
namespace x10 { namespace util { 
template<class FMGL(Key), class FMGL(Value)> class HashMap__HashEntry;
} } 
namespace x10 { namespace util { 
class NoSuchElementException;
} } 
namespace x10 { namespace util { 
template<class FMGL(Key), class FMGL(Value)> class HashMap__HashEntry;
} } 
namespace x10 { namespace util { 
template<class FMGL(Key), class FMGL(Value)> class HashMap__HashEntry;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(Z2), class FMGL(U)> class Fun_0_2;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(Z2), class FMGL(U)> class Fun_0_2;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(Z2), class FMGL(U)> class Fun_0_2;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(Z2), class FMGL(U)> class Fun_0_2;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(Z2), class FMGL(U)> class Fun_0_2;
} } 
namespace x10 { namespace util { 
template<class FMGL(Key), class FMGL(Value)> class HashMap__HashEntry;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(Z2), class FMGL(U)> class Fun_0_2;
} } 
namespace x10 { namespace util { 
template<class FMGL(Key), class FMGL(Value)> class HashMap__HashEntry;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(Z2), class FMGL(U)> class Fun_0_2;
} } 
namespace x10 { namespace util { 
template<class FMGL(T)> class Set;
} } 
namespace x10 { namespace util { 
template<class FMGL(Key), class FMGL(Value)> class HashMap__KeySet;
} } 
namespace x10 { namespace util { 
template<class FMGL(T)> class Set;
} } 
namespace x10 { namespace util { 
template<class FMGL(Key), class FMGL(Val)> class Map__Entry;
} } 
namespace x10 { namespace util { 
template<class FMGL(Key), class FMGL(Value)> class HashMap__EntrySet;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Iterator;
} } 
namespace x10 { namespace util { 
template<class FMGL(Key), class FMGL(Value)> class HashMap__EntriesIterator;
} } 
namespace x10 { namespace util { 
template<class FMGL(Key), class FMGL(Value)> class HashMap__EntriesIterator;
} } 
namespace x10 { namespace io { 
class SerialData;
} } 
namespace x10 { namespace util { 
template<class FMGL(Key), class FMGL(Value)> class HashMap__State;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Iterator;
} } 
namespace x10 { namespace array { 
class Point;
} } 
namespace x10 { namespace util { 
template<class FMGL(T), class FMGL(U)> class Pair;
} } 
#include <x10/util/Pair.struct_h>
namespace x10 { namespace util { 

template<class FMGL(K), class FMGL(V)> class HashMap;
template <> class HashMap<void, void>;
template<class FMGL(K), class FMGL(V)> class HashMap : public x10::lang::Object
  {
    public:
    RTT_H_DECLS_CLASS
    
    static x10aux::itable_entry _itables[4];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    static typename x10::util::Map<FMGL(K), FMGL(V)>::template itable<x10::util::HashMap<FMGL(K), FMGL(V)> > _itable_0;
    
    static x10::lang::Any::itable<x10::util::HashMap<FMGL(K), FMGL(V)> > _itable_1;
    
    static x10::io::CustomSerialization::itable<x10::util::HashMap<FMGL(K), FMGL(V)> > _itable_2;
    
    void _instance_init();
    
    x10aux::ref<x10::lang::Rail<x10aux::ref<x10::util::HashMap__HashEntry<FMGL(K), FMGL(V)> > > >
      FMGL(table);
    
    x10_int FMGL(size);
    
    x10_int FMGL(occupation);
    
    x10_int FMGL(mask);
    
    x10_int FMGL(modCount);
    
    x10_boolean FMGL(shouldRehash);
    
    void _constructor();
    
    static x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> > _make(
             );
    
    void _constructor(x10_int sz);
    
    static x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> > _make(
             x10_int sz);
    
    virtual void init(x10_int sz);
    virtual void clear();
    virtual x10_int hash(FMGL(K) k);
    virtual x10_int hashInternal(FMGL(K) k);
    virtual x10aux::ref<x10::util::Box<FMGL(V)> > __apply(FMGL(K) k);
    virtual x10aux::ref<x10::util::Box<FMGL(V)> > get(FMGL(K) k);
    virtual FMGL(V) getOrElse(FMGL(K) k, FMGL(V) orelse);
    virtual FMGL(V) getOrThrow(FMGL(K) k);
    virtual x10aux::ref<x10::util::HashMap__HashEntry<FMGL(K), FMGL(V)> >
      getEntry(
      FMGL(K) k);
    virtual x10aux::ref<x10::util::Box<FMGL(V)> > put(FMGL(K) k, FMGL(V) v);
    virtual x10aux::ref<x10::util::Box<FMGL(V)> > putInternal(FMGL(K) k,
                                                              FMGL(V) v);
    virtual void rehash();
    virtual void rehashInternal();
    virtual x10_boolean containsKey(FMGL(K) k);
    virtual x10aux::ref<x10::util::Box<FMGL(V)> > remove(FMGL(K) k);
    virtual x10aux::ref<x10::util::Set<FMGL(K)> > keySet();
    virtual x10aux::ref<x10::util::Set<x10aux::ref<x10::util::Map__Entry<FMGL(K), FMGL(V)> > > >
      entries(
      );
    virtual x10aux::ref<x10::lang::Iterator<x10aux::ref<x10::util::HashMap__HashEntry<FMGL(K), FMGL(V)> > > >
      entriesIterator(
      );
    virtual x10_int size();
    void _constructor(x10aux::ref<x10::io::SerialData> x);
    
    static x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> > _make(
             x10aux::ref<x10::io::SerialData> x);
    
    virtual x10aux::ref<x10::io::SerialData> serialize(
      );
    virtual x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >
      x10__util__HashMap____x10__util__HashMap__this(
      );
    void __fieldInitializers2101();
    
    // Serialization
    public: static const x10aux::serialization_id_t _serialization_id;
    
    public: virtual x10aux::serialization_id_t _get_serialization_id() {
         return _serialization_id;
    }
    
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: template<class __T> static x10aux::ref<__T> _deserializer(x10aux::deserialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};
template <> class HashMap<void, void> : public x10::lang::Object
{
    public:
    static x10aux::RuntimeType rtt;
    static const x10aux::RuntimeType* getRTT() { return & rtt; }
    static x10_int
      FMGL(MAX_PROBES);
    
    static x10_int
      FMGL(MIN_SIZE);
    
    static inline x10_int
      FMGL(MAX_PROBES__get)() {
        return x10::util::HashMap<void, void>::FMGL(MAX_PROBES);
    }
    static inline x10_int
      FMGL(MIN_SIZE__get)() {
        return x10::util::HashMap<void, void>::FMGL(MIN_SIZE);
    }
    
};

} } 
#endif // X10_UTIL_HASHMAP_H

namespace x10 { namespace util { 
template<class FMGL(K), class FMGL(V)>
class HashMap;
} } 

#ifndef X10_UTIL_HASHMAP_H_NODEPS
#define X10_UTIL_HASHMAP_H_NODEPS
#include <x10/lang/Object.h>
#include <x10/util/Map.h>
#include <x10/io/CustomSerialization.h>
#include <x10/lang/Rail.h>
#include <x10/util/HashMap__HashEntry.h>
#include <x10/lang/Int.h>
#include <x10/lang/Boolean.h>
#include <x10/lang/Rail.h>
#include <x10/compiler/NonEscaping.h>
#include <x10/compiler/TempNoInline_1.h>
#include <x10/lang/Fun_0_2.h>
#include <x10/util/Box.h>
#include <x10/util/HashMap__HashEntry.h>
#include <x10/util/HashMap__HashEntry.h>
#include <x10/util/HashMap__HashEntry.h>
#include <x10/util/NoSuchElementException.h>
#include <x10/util/HashMap__HashEntry.h>
#include <x10/util/HashMap__HashEntry.h>
#include <x10/lang/Fun_0_2.h>
#include <x10/lang/Fun_0_2.h>
#include <x10/lang/Fun_0_2.h>
#include <x10/lang/Fun_0_2.h>
#include <x10/lang/Fun_0_2.h>
#include <x10/util/HashMap__HashEntry.h>
#include <x10/lang/Fun_0_2.h>
#include <x10/util/HashMap__HashEntry.h>
#include <x10/lang/Fun_0_2.h>
#include <x10/util/Set.h>
#include <x10/util/HashMap__KeySet.h>
#include <x10/util/Set.h>
#include <x10/util/Map__Entry.h>
#include <x10/util/HashMap__EntrySet.h>
#include <x10/lang/Iterator.h>
#include <x10/util/HashMap__EntriesIterator.h>
#include <x10/util/HashMap__EntriesIterator.h>
#include <x10/io/SerialData.h>
#include <x10/util/HashMap__State.h>
#include <x10/lang/Iterator.h>
#include <x10/array/Point.h>
#include <x10/util/Pair.h>
#ifndef X10_UTIL_HASHMAP_H_GENERICS
#define X10_UTIL_HASHMAP_H_GENERICS
template<class FMGL(K), class FMGL(V)> template<class __T> x10aux::ref<__T> x10::util::HashMap<FMGL(K), FMGL(V)>::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> > this_ = new (memset(x10aux::alloc<x10::util::HashMap<FMGL(K), FMGL(V)> >(), 0, sizeof(x10::util::HashMap<FMGL(K), FMGL(V)>))) x10::util::HashMap<FMGL(K), FMGL(V)>();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_UTIL_HASHMAP_H_GENERICS
#ifndef X10_UTIL_HASHMAP_H_IMPLEMENTATION
#define X10_UTIL_HASHMAP_H_IMPLEMENTATION
#include <x10/util/HashMap.h>


template<class FMGL(K), class FMGL(V)> typename x10::util::Map<FMGL(K), FMGL(V)>::template itable<x10::util::HashMap<FMGL(K), FMGL(V)> >  x10::util::HashMap<FMGL(K), FMGL(V)>::_itable_0(&x10::util::HashMap<FMGL(K), FMGL(V)>::clear, &x10::util::HashMap<FMGL(K), FMGL(V)>::containsKey, &x10::util::HashMap<FMGL(K), FMGL(V)>::entries, &x10::util::HashMap<FMGL(K), FMGL(V)>::equals, &x10::util::HashMap<FMGL(K), FMGL(V)>::get, &x10::util::HashMap<FMGL(K), FMGL(V)>::getOrElse, &x10::util::HashMap<FMGL(K), FMGL(V)>::getOrThrow, &x10::util::HashMap<FMGL(K), FMGL(V)>::hashCode, &x10::util::HashMap<FMGL(K), FMGL(V)>::keySet, &x10::util::HashMap<FMGL(K), FMGL(V)>::put, &x10::util::HashMap<FMGL(K), FMGL(V)>::remove, &x10::util::HashMap<FMGL(K), FMGL(V)>::toString, &x10::util::HashMap<FMGL(K), FMGL(V)>::typeName);
template<class FMGL(K), class FMGL(V)> x10::lang::Any::itable<x10::util::HashMap<FMGL(K), FMGL(V)> >  x10::util::HashMap<FMGL(K), FMGL(V)>::_itable_1(&x10::util::HashMap<FMGL(K), FMGL(V)>::equals, &x10::util::HashMap<FMGL(K), FMGL(V)>::hashCode, &x10::util::HashMap<FMGL(K), FMGL(V)>::toString, &x10::util::HashMap<FMGL(K), FMGL(V)>::typeName);
template<class FMGL(K), class FMGL(V)> x10::io::CustomSerialization::itable<x10::util::HashMap<FMGL(K), FMGL(V)> >  x10::util::HashMap<FMGL(K), FMGL(V)>::_itable_2(&x10::util::HashMap<FMGL(K), FMGL(V)>::equals, &x10::util::HashMap<FMGL(K), FMGL(V)>::hashCode, &x10::util::HashMap<FMGL(K), FMGL(V)>::serialize, &x10::util::HashMap<FMGL(K), FMGL(V)>::toString, &x10::util::HashMap<FMGL(K), FMGL(V)>::typeName);
template<class FMGL(K), class FMGL(V)> x10aux::itable_entry x10::util::HashMap<FMGL(K), FMGL(V)>::_itables[4] = {x10aux::itable_entry(&x10aux::getRTT<x10::util::Map<FMGL(K), FMGL(V)> >, &_itable_0), x10aux::itable_entry(&x10aux::getRTT<x10::lang::Any>, &_itable_1), x10aux::itable_entry(&x10aux::getRTT<x10::io::CustomSerialization>, &_itable_2), x10aux::itable_entry(NULL, (void*)x10aux::getRTT<x10::util::HashMap<FMGL(K), FMGL(V)> >())};
template<class FMGL(K), class FMGL(V)> void x10::util::HashMap<FMGL(K), FMGL(V)>::_instance_init() {
    _I_("Doing initialisation for class: x10::util::HashMap<FMGL(K), FMGL(V)>");
    
}


//#line 39 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10FieldDecl_c
/** The actual table, must be of size 2**n */
                                             //#line 42 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10FieldDecl_c
                                             /** Number of non-null, non-removed entries in the table. */
                                                                                                         //#line 45 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10FieldDecl_c
                                                                                                         /** Number of non-null entries in the table. */
                                                                                                                                                        //#line 48 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10FieldDecl_c
                                                                                                                                                        /** table.length - 1 */
                                                                                                                                                                               //#line 50 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10FieldDecl_c
                                                                                                                                                                               
                                                                                                                                                                               //#line 52 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10FieldDecl_c
                                                                                                                                                                               
                                                                                                                                                                               //#line 54 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10FieldDecl_c
                                                                                                                                                                               
                                                                                                                                                                               //#line 55 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10FieldDecl_c
                                                                                                                                                                               

//#line 57 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10ConstructorDecl_c
template<class FMGL(K), class FMGL(V)> void x10::util::HashMap<FMGL(K), FMGL(V)>::_constructor(
                                         ) {
    this->::x10::lang::Object::_constructor();
    
    //#line 19 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this)->x10::util::HashMap<FMGL(K), FMGL(V)>::__fieldInitializers2101();
    
    //#line 58 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this)->x10::util::HashMap<FMGL(K), FMGL(V)>::init(
      x10::util::HashMap<void, void>::
        FMGL(MIN_SIZE__get)());
    
}
template<class FMGL(K), class FMGL(V)> x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> > x10::util::HashMap<FMGL(K), FMGL(V)>::_make(
                                         ) {
    x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> > this_ = new (memset(x10aux::alloc<x10::util::HashMap<FMGL(K), FMGL(V)> >(), 0, sizeof(x10::util::HashMap<FMGL(K), FMGL(V)>))) x10::util::HashMap<FMGL(K), FMGL(V)>();
    this_->_constructor();
    return this_;
}



//#line 61 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10ConstructorDecl_c
template<class FMGL(K), class FMGL(V)> void x10::util::HashMap<FMGL(K), FMGL(V)>::_constructor(
                                         x10_int sz)
{
    this->::x10::lang::Object::_constructor();
    
    //#line 19 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this)->x10::util::HashMap<FMGL(K), FMGL(V)>::__fieldInitializers2101();
    
    //#line 62 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10LocalDecl_c
    x10_int pow2 =
      x10::util::HashMap<void, void>::
        FMGL(MIN_SIZE__get)();
    
    //#line 63 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10While_c
    while (((pow2) < (sz)))
    {
        
        //#line 64 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": polyglot.ast.Eval_c
        pow2 =
          ((x10_int) ((pow2) << (((x10_int)1))));
    }
    
    //#line 65 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this)->x10::util::HashMap<FMGL(K), FMGL(V)>::init(
      pow2);
    
}
template<class FMGL(K), class FMGL(V)>
x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> > x10::util::HashMap<FMGL(K), FMGL(V)>::_make(
  x10_int sz)
{
    x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> > this_ = new (memset(x10aux::alloc<x10::util::HashMap<FMGL(K), FMGL(V)> >(), 0, sizeof(x10::util::HashMap<FMGL(K), FMGL(V)>))) x10::util::HashMap<FMGL(K), FMGL(V)>();
    this_->_constructor(sz);
    return this_;
}



//#line 68 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10MethodDecl_c
template<class FMGL(K), class FMGL(V)> void
  x10::util::HashMap<FMGL(K), FMGL(V)>::init(
  x10_int sz) {
    
    //#line 70 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": polyglot.ast.Assert_c
    #ifndef NO_ASSERTIONS
    if (x10aux::x10__assertions_enabled)
        x10aux::x10__assert((x10aux::struct_equals(((x10_int) ((sz) & (((x10_int) -(sz))))),
                                                   sz)));
    #endif//NO_ASSERTIONS
    
    //#line 71 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": polyglot.ast.Assert_c
    #ifndef NO_ASSERTIONS
    if (x10aux::x10__assertions_enabled)
        x10aux::x10__assert(((sz) >= (((x10_int)4))));
    #endif//NO_ASSERTIONS
    
    //#line 73 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this)->
      FMGL(table) = x10::lang::Rail<void>::make<x10aux::ref<x10::util::HashMap__HashEntry<FMGL(K), FMGL(V)> > >(sz);
    
    //#line 74 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this)->
      FMGL(mask) = ((x10_int) ((sz) - (((x10_int)1))));
    
    //#line 75 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this)->
      FMGL(size) = ((x10_int)0);
    
    //#line 76 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this)->
      FMGL(occupation) = ((x10_int)0);
    
    //#line 77 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this)->
      FMGL(shouldRehash) = false;
}

//#line 80 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10MethodDecl_c
template<class FMGL(K), class FMGL(V)> void
  x10::util::HashMap<FMGL(K), FMGL(V)>::clear(
  ) {
    
    //#line 81 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": polyglot.ast.Eval_c
    (__extension__ ({
        x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> > x =
          ((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this);
        x10_int y =
          ((x10_int)1);
        x10aux::nullCheck(x)->
          FMGL(modCount) =
          ((x10_int) ((x10aux::nullCheck(x)->
                         FMGL(modCount)) + (y)));
    }))
    ;
    
    //#line 82 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this)->x10::util::HashMap<FMGL(K), FMGL(V)>::init(
      ((x10_int)4));
}

//#line 85 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10MethodDecl_c
template<class FMGL(K), class FMGL(V)> x10_int
  x10::util::HashMap<FMGL(K), FMGL(V)>::hash(
  FMGL(K) k) {
    
    //#line 85 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10Return_c
    return ((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this)->x10::util::HashMap<FMGL(K), FMGL(V)>::hashInternal(
             k);
    
}

//#line 86 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10MethodDecl_c
template<class FMGL(K), class FMGL(V)> x10_int
  x10::util::HashMap<FMGL(K), FMGL(V)>::hashInternal(
  FMGL(K) k) {
    
    //#line 87 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10Return_c
    return ((x10_int) ((x10aux::hash_code(k)) * (((x10_int)17))));
    
}

//#line 90 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10MethodDecl_c
template<class FMGL(K), class FMGL(V)> x10aux::ref<x10::util::Box<FMGL(V)> >
  x10::util::HashMap<FMGL(K), FMGL(V)>::__apply(
  FMGL(K) k) {
    
    //#line 90 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10Return_c
    return ((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this)->get(
             k);
    
}

//#line 92 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10MethodDecl_c
template<class FMGL(K), class FMGL(V)> x10aux::ref<x10::util::Box<FMGL(V)> >
  x10::util::HashMap<FMGL(K), FMGL(V)>::get(
  FMGL(K) k) {
    
    //#line 93 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10LocalDecl_c
    x10aux::ref<x10::util::HashMap__HashEntry<FMGL(K), FMGL(V)> > e =
      ((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this)->getEntry(
        k);
    
    //#line 94 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10If_c
    if ((x10aux::struct_equals(e, X10_NULL)) ||
        x10aux::nullCheck(e)->
          FMGL(removed)) {
        
        //#line 94 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10Return_c
        return x10aux::class_cast_unchecked<x10aux::ref<x10::util::Box<FMGL(V)> > >(X10_NULL);
        
    }
    
    //#line 95 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10Return_c
    return x10::util::Box<void>::template __implicit_convert<FMGL(V) >(
             x10aux::nullCheck(e)->
               FMGL(value));
    
}

//#line 98 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10MethodDecl_c
template<class FMGL(K), class FMGL(V)> FMGL(V)
  x10::util::HashMap<FMGL(K), FMGL(V)>::getOrElse(
  FMGL(K) k,
  FMGL(V) orelse) {
    
    //#line 99 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10LocalDecl_c
    x10aux::ref<x10::util::HashMap__HashEntry<FMGL(K), FMGL(V)> > e =
      ((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this)->getEntry(
        k);
    
    //#line 100 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10If_c
    if ((x10aux::struct_equals(e, X10_NULL)) ||
        x10aux::nullCheck(e)->
          FMGL(removed)) {
        
        //#line 100 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10Return_c
        return orelse;
        
    }
    
    //#line 101 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10Return_c
    return x10aux::nullCheck(e)->FMGL(value);
    
}

//#line 104 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10MethodDecl_c
template<class FMGL(K), class FMGL(V)> FMGL(V)
  x10::util::HashMap<FMGL(K), FMGL(V)>::getOrThrow(
  FMGL(K) k) {
    
    //#line 106 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10LocalDecl_c
    x10aux::ref<x10::util::HashMap__HashEntry<FMGL(K), FMGL(V)> > e =
      ((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this)->getEntry(
        k);
    
    //#line 107 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10If_c
    if ((x10aux::struct_equals(e, X10_NULL)) ||
        x10aux::nullCheck(e)->
          FMGL(removed)) {
        
        //#line 107 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": polyglot.ast.Throw_c
        x10aux::throwException(x10aux::nullCheck(x10::util::NoSuchElementException::_make(x10aux::string_utils::lit("Not found"))));
    }
    
    //#line 108 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10Return_c
    return x10aux::nullCheck(e)->FMGL(value);
    
}

//#line 111 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10MethodDecl_c
template<class FMGL(K), class FMGL(V)> x10aux::ref<x10::util::HashMap__HashEntry<FMGL(K), FMGL(V)> >
  x10::util::HashMap<FMGL(K), FMGL(V)>::getEntry(
  FMGL(K) k) {
    
    //#line 112 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10If_c
    if ((x10aux::struct_equals(((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this)->
                                 FMGL(size),
                               ((x10_int)0))))
    {
        
        //#line 113 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10Return_c
        return x10aux::class_cast_unchecked<x10aux::ref<x10::util::HashMap__HashEntry<FMGL(K), FMGL(V)> > >(X10_NULL);
        
    }
    
    //#line 119 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10LocalDecl_c
    x10_int h = ((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this)->hash(
                  k);
    
    //#line 121 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10LocalDecl_c
    x10_int i = h;
    
    //#line 123 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10While_c
    while (true) {
        
        //#line 124 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10LocalDecl_c
        x10_int j = ((x10_int) ((i) & (((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this)->
                                         FMGL(mask))));
        
        //#line 125 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": polyglot.ast.Eval_c
        i = ((x10_int) ((i) + (((x10_int)1))));
        
        //#line 127 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10LocalDecl_c
        x10aux::ref<x10::util::HashMap__HashEntry<FMGL(K), FMGL(V)> > e =
          (((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this)->
             FMGL(table))->__apply(j);
        
        //#line 128 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10If_c
        if ((x10aux::struct_equals(e, X10_NULL)))
        {
            
            //#line 129 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10If_c
            if (((((x10_int) ((i) - (h)))) > (((x10_int)3))))
            {
                
                //#line 130 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": polyglot.ast.Eval_c
                ((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this)->
                  FMGL(shouldRehash) =
                  true;
            }
            
            //#line 131 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10Return_c
            return x10aux::class_cast_unchecked<x10aux::ref<x10::util::HashMap__HashEntry<FMGL(K), FMGL(V)> > >(X10_NULL);
            
        }
        
        //#line 133 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10If_c
        if ((!x10aux::struct_equals(e, X10_NULL)))
        {
            
            //#line 134 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10If_c
            if ((x10aux::struct_equals(x10aux::nullCheck(e)->
                                         FMGL(hash),
                                       h)) &&
                x10aux::equals(k,x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Any> >(x10aux::nullCheck(e)->
                                                                                              FMGL(key))))
            {
                
                //#line 135 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10If_c
                if (((((x10_int) ((i) - (h)))) > (((x10_int)3))))
                {
                    
                    //#line 136 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": polyglot.ast.Eval_c
                    ((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this)->
                      FMGL(shouldRehash) =
                      true;
                }
                
                //#line 137 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10Return_c
                return e;
                
            }
            
            //#line 139 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10If_c
            if (((((x10_int) ((i) - (h)))) > (x10aux::nullCheck(((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this)->
                                                                  FMGL(table))->
                                                FMGL(length))))
            {
                
                //#line 140 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10If_c
                if (((((x10_int) ((i) - (h)))) > (((x10_int)3))))
                {
                    
                    //#line 141 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": polyglot.ast.Eval_c
                    ((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this)->
                      FMGL(shouldRehash) =
                      true;
                }
                
                //#line 142 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10Return_c
                return x10aux::class_cast_unchecked<x10aux::ref<x10::util::HashMap__HashEntry<FMGL(K), FMGL(V)> > >(X10_NULL);
                
            }
            
        }
        
    }
    
}

//#line 148 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10MethodDecl_c
template<class FMGL(K), class FMGL(V)> x10aux::ref<x10::util::Box<FMGL(V)> >
  x10::util::HashMap<FMGL(K), FMGL(V)>::put(
  FMGL(K) k,
  FMGL(V) v) {
    
    //#line 148 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10Return_c
    return ((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this)->x10::util::HashMap<FMGL(K), FMGL(V)>::putInternal(
             k,
             v);
    
}

//#line 149 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10MethodDecl_c
template<class FMGL(K), class FMGL(V)> x10aux::ref<x10::util::Box<FMGL(V)> >
  x10::util::HashMap<FMGL(K), FMGL(V)>::putInternal(
  FMGL(K) k,
  FMGL(V) v) {
    
    //#line 150 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10If_c
    if ((x10aux::struct_equals(((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this)->
                                 FMGL(occupation),
                               x10aux::nullCheck(((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this)->
                                                   FMGL(table))->
                                 FMGL(length))) ||
        ((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this)->
          FMGL(shouldRehash) &&
        ((((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this)->
            FMGL(occupation)) >= (((x10_int) ((x10aux::nullCheck(((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this)->
                                                                   FMGL(table))->
                                                 FMGL(length)) / x10aux::zeroCheck(((x10_int)2)))))))
    {
        
        //#line 151 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": polyglot.ast.Eval_c
        ((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this)->x10::util::HashMap<FMGL(K), FMGL(V)>::rehashInternal();
    }
    
    //#line 153 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10LocalDecl_c
    x10_int h = ((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this)->x10::util::HashMap<FMGL(K), FMGL(V)>::hashInternal(
                  k);
    
    //#line 154 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10LocalDecl_c
    x10_int i = h;
    
    //#line 156 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10While_c
    while (true) {
        
        //#line 157 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10LocalDecl_c
        x10_int j = ((x10_int) ((i) & (((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this)->
                                         FMGL(mask))));
        
        //#line 158 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": polyglot.ast.Eval_c
        i = ((x10_int) ((i) + (((x10_int)1))));
        
        //#line 160 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10LocalDecl_c
        x10aux::ref<x10::util::HashMap__HashEntry<FMGL(K), FMGL(V)> > e =
          (((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this)->
             FMGL(table))->__apply(j);
        
        //#line 161 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10If_c
        if ((x10aux::struct_equals(e, X10_NULL)))
        {
            
            //#line 162 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10If_c
            if (((((x10_int) ((i) - (h)))) > (((x10_int)3))))
            {
                
                //#line 163 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": polyglot.ast.Eval_c
                ((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this)->
                  FMGL(shouldRehash) =
                  true;
            }
            
            //#line 164 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": polyglot.ast.Eval_c
            (__extension__ ({
                x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> > x =
                  ((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this);
                x10_int y =
                  ((x10_int)1);
                x10aux::nullCheck(x)->
                  FMGL(modCount) =
                  ((x10_int) ((x10aux::nullCheck(x)->
                                 FMGL(modCount)) + (y)));
            }))
            ;
            
            //#line 165 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": polyglot.ast.Eval_c
            (((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this)->
               FMGL(table))->__set(x10::util::HashMap__HashEntry<FMGL(K), FMGL(V)>::_make(k,
                                                                                          v,
                                                                                          h), j);
            
            //#line 166 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": polyglot.ast.Eval_c
            (__extension__ ({
                x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> > x =
                  ((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this);
                x10_int y =
                  ((x10_int)1);
                x10aux::nullCheck(x)->
                  FMGL(size) =
                  ((x10_int) ((x10aux::nullCheck(x)->
                                 FMGL(size)) + (y)));
            }))
            ;
            
            //#line 167 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": polyglot.ast.Eval_c
            (__extension__ ({
                x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> > x =
                  ((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this);
                x10_int y =
                  ((x10_int)1);
                x10aux::nullCheck(x)->
                  FMGL(occupation) =
                  ((x10_int) ((x10aux::nullCheck(x)->
                                 FMGL(occupation)) + (y)));
            }))
            ;
            
            //#line 168 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10Return_c
            return x10aux::class_cast_unchecked<x10aux::ref<x10::util::Box<FMGL(V)> > >(X10_NULL);
            
        }
        else
        
        //#line 169 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10If_c
        if ((x10aux::struct_equals(x10aux::nullCheck(e)->
                                     FMGL(hash),
                                   h)) &&
            x10aux::equals(k,x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Any> >(x10aux::nullCheck(e)->
                                                                                          FMGL(key))))
        {
            
            //#line 172 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10LocalDecl_c
            FMGL(V) old =
              x10aux::nullCheck(e)->
                FMGL(value);
            
            //#line 173 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": polyglot.ast.Eval_c
            x10aux::nullCheck(e)->
              FMGL(value) =
              v;
            
            //#line 174 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10If_c
            if (x10aux::nullCheck(e)->
                  FMGL(removed))
            {
                
                //#line 175 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": polyglot.ast.Eval_c
                x10aux::nullCheck(e)->
                  FMGL(removed) =
                  false;
                
                //#line 176 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": polyglot.ast.Eval_c
                (__extension__ ({
                    x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> > x =
                      ((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this);
                    x10_int y =
                      ((x10_int)1);
                    x10aux::nullCheck(x)->
                      FMGL(size) =
                      ((x10_int) ((x10aux::nullCheck(x)->
                                     FMGL(size)) + (y)));
                }))
                ;
                
                //#line 177 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10Return_c
                return x10aux::class_cast_unchecked<x10aux::ref<x10::util::Box<FMGL(V)> > >(X10_NULL);
                
            }
            
            //#line 179 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10Return_c
            return x10::util::Box<void>::template __implicit_convert<FMGL(V) >(
                     old);
            
        }
        
    }
    
}

//#line 184 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10MethodDecl_c
template<class FMGL(K), class FMGL(V)> void
  x10::util::HashMap<FMGL(K), FMGL(V)>::rehash(
  ) {
    {
        
        //#line 184 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": polyglot.ast.Eval_c
        ((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this)->x10::util::HashMap<FMGL(K), FMGL(V)>::rehashInternal();
        
        //#line 184 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10Return_c
        return;
    }
}

//#line 185 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10MethodDecl_c
template<class FMGL(K), class FMGL(V)> void
  x10::util::HashMap<FMGL(K), FMGL(V)>::rehashInternal(
  ) {
    
    //#line 186 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": polyglot.ast.Eval_c
    (__extension__ ({
        x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> > x =
          ((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this);
        x10_int y =
          ((x10_int)1);
        x10aux::nullCheck(x)->
          FMGL(modCount) =
          ((x10_int) ((x10aux::nullCheck(x)->
                         FMGL(modCount)) + (y)));
    }))
    ;
    
    //#line 187 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10LocalDecl_c
    x10aux::ref<x10::lang::Rail<x10aux::ref<x10::util::HashMap__HashEntry<FMGL(K), FMGL(V)> > > > t =
      ((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this)->
        FMGL(table);
    
    //#line 188 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10LocalDecl_c
    x10_int oldSize = ((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this)->
                        FMGL(size);
    
    //#line 189 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this)->
      FMGL(table) = x10::lang::Rail<void>::make<x10aux::ref<x10::util::HashMap__HashEntry<FMGL(K), FMGL(V)> > >(((x10_int) ((x10aux::nullCheck(t)->
                                                                                                                               FMGL(length)) * (((x10_int)2)))));
    
    //#line 190 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this)->
      FMGL(mask) = ((x10_int) ((x10aux::nullCheck(((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this)->
                                                    FMGL(table))->
                                  FMGL(length)) - (((x10_int)1))));
    
    //#line 191 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this)->
      FMGL(size) = ((x10_int)0);
    
    //#line 192 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this)->
      FMGL(occupation) = ((x10_int)0);
    
    //#line 193 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this)->
      FMGL(shouldRehash) = false;
    
    //#line 195 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": polyglot.ast.For_c
    {
        x10_int i;
        for (
             //#line 195 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10LocalDecl_c
             i = ((x10_int)0); ((i) < (x10aux::nullCheck(t)->
                                         FMGL(length)));
             
             //#line 195 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": polyglot.ast.Eval_c
             i =
               ((x10_int) ((i) + (((x10_int)1)))))
        {
            
            //#line 196 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10If_c
            if ((!x10aux::struct_equals((t)->__apply(i),
                                        X10_NULL)) &&
                !(x10aux::nullCheck((t)->__apply(i))->
                    FMGL(removed)))
            {
                
                //#line 197 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": polyglot.ast.Eval_c
                ((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this)->x10::util::HashMap<FMGL(K), FMGL(V)>::putInternal(
                  x10aux::nullCheck((t)->__apply(i))->
                    FMGL(key),
                  x10aux::nullCheck((t)->__apply(i))->
                    FMGL(value));
                
                //#line 198 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": polyglot.ast.Eval_c
                ((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this)->
                  FMGL(shouldRehash) =
                  false;
            }
            
        }
    }
    
    //#line 201 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": polyglot.ast.Assert_c
    #ifndef NO_ASSERTIONS
    if (x10aux::x10__assertions_enabled)
        x10aux::x10__assert((x10aux::struct_equals(((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this)->
                                                     FMGL(size),
                                                   oldSize)));
    #endif//NO_ASSERTIONS
    
    //#line 202 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this)->
      FMGL(size) = oldSize;
}

//#line 205 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10MethodDecl_c
template<class FMGL(K), class FMGL(V)> x10_boolean
  x10::util::HashMap<FMGL(K), FMGL(V)>::containsKey(
  FMGL(K) k) {
    
    //#line 206 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10LocalDecl_c
    x10aux::ref<x10::util::HashMap__HashEntry<FMGL(K), FMGL(V)> > e =
      ((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this)->getEntry(
        k);
    
    //#line 207 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10Return_c
    return (!x10aux::struct_equals(e, X10_NULL)) &&
    !(x10aux::nullCheck(e)->
        FMGL(removed));
    
}

//#line 210 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10MethodDecl_c
template<class FMGL(K), class FMGL(V)> x10aux::ref<x10::util::Box<FMGL(V)> >
  x10::util::HashMap<FMGL(K), FMGL(V)>::remove(
  FMGL(K) k) {
    
    //#line 211 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": polyglot.ast.Eval_c
    (__extension__ ({
        x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> > x =
          ((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this);
        x10_int y =
          ((x10_int)1);
        x10aux::nullCheck(x)->
          FMGL(modCount) =
          ((x10_int) ((x10aux::nullCheck(x)->
                         FMGL(modCount)) + (y)));
    }))
    ;
    
    //#line 212 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10LocalDecl_c
    x10aux::ref<x10::util::HashMap__HashEntry<FMGL(K), FMGL(V)> > e =
      ((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this)->getEntry(
        k);
    
    //#line 213 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10If_c
    if ((!x10aux::struct_equals(e, X10_NULL)) &&
        !(x10aux::nullCheck(e)->
            FMGL(removed))) {
        
        //#line 214 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": polyglot.ast.Eval_c
        (__extension__ ({
            x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> > x =
              ((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this);
            x10_int y =
              ((x10_int)1);
            x10aux::nullCheck(x)->
              FMGL(size) =
              ((x10_int) ((x10aux::nullCheck(x)->
                             FMGL(size)) - (y)));
        }))
        ;
        
        //#line 215 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": polyglot.ast.Eval_c
        x10aux::nullCheck(e)->FMGL(removed) =
          true;
        
        //#line 216 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10Return_c
        return x10::util::Box<void>::template __implicit_convert<FMGL(V) >(
                 x10aux::nullCheck(e)->
                   FMGL(value));
        
    }
    
    //#line 218 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10Return_c
    return x10aux::class_cast_unchecked<x10aux::ref<x10::util::Box<FMGL(V)> > >(X10_NULL);
    
}

//#line 221 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10MethodDecl_c
template<class FMGL(K), class FMGL(V)> x10aux::ref<x10::util::Set<FMGL(K)> >
  x10::util::HashMap<FMGL(K), FMGL(V)>::keySet(
  ) {
    
    //#line 221 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10Return_c
    return x10aux::class_cast_unchecked<x10aux::ref<x10::util::Set<FMGL(K)> > >(x10::util::HashMap__KeySet<FMGL(K), FMGL(V)>::_make(((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this)));
    
}

//#line 222 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10MethodDecl_c
template<class FMGL(K), class FMGL(V)> x10aux::ref<x10::util::Set<x10aux::ref<x10::util::Map__Entry<FMGL(K), FMGL(V)> > > >
  x10::util::HashMap<FMGL(K), FMGL(V)>::entries(
  ) {
    
    //#line 222 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10Return_c
    return x10aux::class_cast_unchecked<x10aux::ref<x10::util::Set<x10aux::ref<x10::util::Map__Entry<FMGL(K), FMGL(V)> > > > >(x10::util::HashMap__EntrySet<FMGL(K), FMGL(V)>::_make(((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this)));
    
}

//#line 224 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10MethodDecl_c
template<class FMGL(K), class FMGL(V)> x10aux::ref<x10::lang::Iterator<x10aux::ref<x10::util::HashMap__HashEntry<FMGL(K), FMGL(V)> > > >
  x10::util::HashMap<FMGL(K), FMGL(V)>::entriesIterator(
  ) {
    
    //#line 225 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10LocalDecl_c
    x10aux::ref<x10::util::HashMap__EntriesIterator<FMGL(K), FMGL(V)> > iterator =
      x10::util::HashMap__EntriesIterator<FMGL(K), FMGL(V)>::_make(((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this));
    
    //#line 226 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": polyglot.ast.Eval_c
    iterator->advance();
    
    //#line 227 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10Return_c
    return x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Iterator<x10aux::ref<x10::util::HashMap__HashEntry<FMGL(K), FMGL(V)> > > > >(iterator);
    
}

//#line 263 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10MethodDecl_c
template<class FMGL(K), class FMGL(V)> x10_int
  x10::util::HashMap<FMGL(K), FMGL(V)>::size(
  ) {
    
    //#line 263 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10Return_c
    return ((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this)->
             FMGL(size);
    
}

//#line 322 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10ConstructorDecl_c
template<class FMGL(K), class FMGL(V)> void x10::util::HashMap<FMGL(K), FMGL(V)>::_constructor(
                                         x10aux::ref<x10::io::SerialData> x)
{
    this->_constructor();
    
    //#line 324 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10LocalDecl_c
    x10aux::ref<x10::util::HashMap__State<FMGL(K), FMGL(V)> > state =
      x10aux::class_cast<x10aux::ref<x10::util::HashMap__State<FMGL(K), FMGL(V)> > >(x10aux::nullCheck(x)->
                                                                                       FMGL(data));
    
    //#line 325 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": polyglot.ast.For_c
    {
        x10aux::ref<x10::lang::Iterator<x10aux::ref<x10::array::Point> > > p2103;
        for (
             //#line 325 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10LocalDecl_c
             p2103 =
               x10aux::nullCheck(x10aux::nullCheck(state)->
                                   FMGL(content))->
                 FMGL(region)->iterator();
             x10::lang::Iterator<x10aux::ref<x10::array::Point> >::hasNext(x10aux::nullCheck(p2103));
             )
        {
            
            //#line 325 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10LocalDecl_c
            x10aux::ref<x10::array::Point> p =
              x10::lang::Iterator<x10aux::ref<x10::array::Point> >::next(x10aux::nullCheck(p2103));
            
            //#line 326 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10LocalDecl_c
            x10::util::Pair<FMGL(K), FMGL(V)> pair =
              x10aux::nullCheck(x10aux::nullCheck(state)->
                                  FMGL(content))->x10::array::Array<x10::util::Pair<FMGL(K), FMGL(V)> >::__apply(
                p);
            
            //#line 327 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": polyglot.ast.Eval_c
            ((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this)->x10::util::HashMap<FMGL(K), FMGL(V)>::putInternal(
              pair->
                FMGL(first),
              pair->
                FMGL(second));
        }
    }
    
}
template<class FMGL(K), class FMGL(V)>
x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> > x10::util::HashMap<FMGL(K), FMGL(V)>::_make(
  x10aux::ref<x10::io::SerialData> x)
{
    x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> > this_ = new (memset(x10aux::alloc<x10::util::HashMap<FMGL(K), FMGL(V)> >(), 0, sizeof(x10::util::HashMap<FMGL(K), FMGL(V)>))) x10::util::HashMap<FMGL(K), FMGL(V)>();
    this_->_constructor(x);
    return this_;
}



//#line 334 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10MethodDecl_c
template<class FMGL(K), class FMGL(V)> x10aux::ref<x10::io::SerialData>
  x10::util::HashMap<FMGL(K), FMGL(V)>::serialize(
  ) {
    
    //#line 334 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10Return_c
    return x10::io::SerialData::_make(x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Any> >(x10::util::HashMap__State<FMGL(K), FMGL(V)>::_make(((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this))),
                                      x10aux::class_cast_unchecked<x10aux::ref<x10::io::SerialData> >(X10_NULL));
    
}

//#line 19 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10MethodDecl_c
template<class FMGL(K), class FMGL(V)> x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >
  x10::util::HashMap<FMGL(K), FMGL(V)>::x10__util__HashMap____x10__util__HashMap__this(
  ) {
    
    //#line 19 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10Return_c
    return ((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this);
    
}

//#line 19 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10MethodDecl_c
template<class FMGL(K), class FMGL(V)> void
  x10::util::HashMap<FMGL(K), FMGL(V)>::__fieldInitializers2101(
  ) {
    
    //#line 19 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this)->
      FMGL(table) = x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Rail<x10aux::ref<x10::util::HashMap__HashEntry<FMGL(K), FMGL(V)> > > > >(X10_NULL);
    
    //#line 19 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this)->
      FMGL(size) = ((x10_int)0);
    
    //#line 19 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this)->
      FMGL(occupation) = ((x10_int)0);
    
    //#line 19 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this)->
      FMGL(mask) = ((x10_int)0);
    
    //#line 19 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this)->
      FMGL(modCount) = ((x10_int)0);
    
    //#line 19 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::util::HashMap<FMGL(K), FMGL(V)> >)this)->
      FMGL(shouldRehash) = false;
}
template<class FMGL(K), class FMGL(V)> const x10aux::serialization_id_t x10::util::HashMap<FMGL(K), FMGL(V)>::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(x10::util::HashMap<FMGL(K), FMGL(V)>::template _deserializer<x10::lang::Reference>, x10aux::CLOSURE_KIND_NOT_ASYNC);

template<class FMGL(K), class FMGL(V)> void x10::util::HashMap<FMGL(K), FMGL(V)>::_serialize_body(x10aux::serialization_buffer& buf) {
    /* NOTE: Implements x10.io.CustomSerialization */
    buf.write(this->serialize());
    
}

template<class FMGL(K), class FMGL(V)> void x10::util::HashMap<FMGL(K), FMGL(V)>::_deserialize_body(x10aux::deserialization_buffer& buf) {
    /* NOTE: Implements x10.io.CustomSerialization */
    x10aux::ref<x10::io::SerialData>val_ = buf.read<x10aux::ref<x10::io::SerialData> >();
    _constructor(val_);
    
}

template<class FMGL(K), class FMGL(V)> x10aux::RuntimeType x10::util::HashMap<FMGL(K), FMGL(V)>::rtt;
template<class FMGL(K), class FMGL(V)> void x10::util::HashMap<FMGL(K), FMGL(V)>::_initRTT() {
    const x10aux::RuntimeType *canonical = x10aux::getRTT<x10::util::HashMap<void, void> >();
    if (rtt.initStageOne(canonical)) return;
    const x10aux::RuntimeType* parents[3] = { x10aux::getRTT<x10::lang::Object>(), x10aux::getRTT<x10::util::Map<FMGL(K), FMGL(V)> >(), x10aux::getRTT<x10::io::CustomSerialization>()};
    const x10aux::RuntimeType* params[2] = { x10aux::getRTT<FMGL(K)>(), x10aux::getRTT<FMGL(V)>()};
    x10aux::RuntimeType::Variance variances[2] = { x10aux::RuntimeType::invariant, x10aux::RuntimeType::invariant};
    const char *baseName = "x10.util.HashMap";
    rtt.initStageTwo(baseName, x10aux::RuntimeType::class_kind, 3, parents, 2, params, variances);
}
#endif // X10_UTIL_HASHMAP_H_IMPLEMENTATION
#endif // __X10_UTIL_HASHMAP_H_NODEPS
