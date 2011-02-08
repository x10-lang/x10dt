#ifndef __X10_UTIL_WORKERLOCALSTORAGE_H
#define __X10_UTIL_WORKERLOCALSTORAGE_H

#include <x10rt.h>


#define X10_LANG_OBJECT_H_NODEPS
#include <x10/lang/Object.h>
#undef X10_LANG_OBJECT_H_NODEPS
#define X10_LANG_PLACELOCALHANDLE_STRUCT_H_NODEPS
#include <x10/lang/PlaceLocalHandle.struct_h>
#undef X10_LANG_PLACELOCALHANDLE_STRUCT_H_NODEPS
namespace x10 { namespace lang { 
template<class FMGL(T)> class PlaceLocalHandle;
} } 
#include <x10/lang/PlaceLocalHandle.struct_h>
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace util { 
template<class FMGL(K), class FMGL(V)> class HashMap;
} } 
namespace x10 { namespace util { 
template<class FMGL(T)> class Box;
} } 
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace lang { 
class Runtime;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace util { 
template<class FMGL(K), class FMGL(V)> class HashMap;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace util { 
template<class FMGL(K), class FMGL(V)> class HashMap;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace util { 
template<class FMGL(K), class FMGL(V)> class HashMap;
} } 
namespace x10 { namespace util { 
class NoSuchElementException;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace util { 
template<class FMGL(K), class FMGL(V)> class HashMap;
} } 
namespace x10 { namespace util { 
template<class FMGL(K), class FMGL(V)> class HashMap;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace util { 
template<class FMGL(K), class FMGL(V)> class HashMap;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class PlaceLocalHandle;
} } 
#include <x10/lang/PlaceLocalHandle.struct_h>
namespace x10 { namespace array { 
class Dist;
} } 
namespace x10 { namespace lang { 
template<class FMGL(U)> class Fun_0_0;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace util { 
template<class FMGL(K), class FMGL(V)> class HashMap;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace util { 
template<class FMGL(K), class FMGL(V)> class HashMap;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace util { 

template<class FMGL(Key), class FMGL(Value)> class WorkerLocalStorage;
template <> class WorkerLocalStorage<void, void>;
template<class FMGL(Key), class FMGL(Value)> class WorkerLocalStorage : public x10::lang::Object
  {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    x10::lang::PlaceLocalHandle<x10aux::ref<x10::array::Array<x10aux::ref<x10::util::HashMap<FMGL(Key), FMGL(Value)> > > > >
      FMGL(store);
    
    virtual x10aux::ref<x10::util::Box<FMGL(Value)> > get(
      FMGL(Key) key);
    virtual FMGL(Value) getOrElse(FMGL(Key) key, FMGL(Value) value);
    virtual FMGL(Value) getOrThrow(FMGL(Key) key);
    virtual x10aux::ref<x10::util::Box<FMGL(Value)> > put(
      FMGL(Key) key,
      FMGL(Value) value);
    virtual x10aux::ref<x10::util::Box<FMGL(Value)> > remove(
      FMGL(Key) key);
    virtual x10aux::ref<x10::util::WorkerLocalStorage<FMGL(Key), FMGL(Value)> >
      x10__util__WorkerLocalStorage____x10__util__WorkerLocalStorage__this(
      );
    void _constructor();
    
    static x10aux::ref<x10::util::WorkerLocalStorage<FMGL(Key), FMGL(Value)> > _make(
             );
    
    void __fieldInitializers2235();
    
    // Serialization
    public: static const x10aux::serialization_id_t _serialization_id;
    
    public: virtual x10aux::serialization_id_t _get_serialization_id() {
         return _serialization_id;
    }
    
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: template<class __T> static x10aux::ref<__T> _deserializer(x10aux::deserialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};
template <> class WorkerLocalStorage<void, void> : public x10::lang::Object
{
    public:
    static x10aux::RuntimeType rtt;
    static const x10aux::RuntimeType* getRTT() { return & rtt; }
    
};

} } 
#endif // X10_UTIL_WORKERLOCALSTORAGE_H

namespace x10 { namespace util { 
template<class FMGL(Key), class FMGL(Value)>
class WorkerLocalStorage;
} } 

#ifndef X10_UTIL_WORKERLOCALSTORAGE_H_NODEPS
#define X10_UTIL_WORKERLOCALSTORAGE_H_NODEPS
#include <x10/lang/Object.h>
#include <x10/lang/PlaceLocalHandle.h>
#include <x10/array/Array.h>
#include <x10/util/HashMap.h>
#include <x10/util/Box.h>
#include <x10/lang/Int.h>
#include <x10/lang/Runtime.h>
#include <x10/array/Array.h>
#include <x10/util/HashMap.h>
#include <x10/array/Array.h>
#include <x10/util/HashMap.h>
#include <x10/array/Array.h>
#include <x10/util/HashMap.h>
#include <x10/util/NoSuchElementException.h>
#include <x10/array/Array.h>
#include <x10/util/HashMap.h>
#include <x10/util/HashMap.h>
#include <x10/array/Array.h>
#include <x10/util/HashMap.h>
#include <x10/lang/PlaceLocalHandle.h>
#include <x10/array/Dist.h>
#include <x10/lang/Fun_0_0.h>
#include <x10/array/Array.h>
#include <x10/util/HashMap.h>
#include <x10/array/Array.h>
#include <x10/util/HashMap.h>
#include <x10/array/Array.h>
#ifndef X10_UTIL_WORKERLOCALSTORAGE__CLOSURE__0_CLOSURE
#define X10_UTIL_WORKERLOCALSTORAGE__CLOSURE__0_CLOSURE
#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_0.h>
template<class FMGL(Key), class FMGL(Value)> class x10_util_WorkerLocalStorage__closure__0 : public x10::lang::Closure {
    public:
    
    static typename x10::lang::Fun_0_0<x10aux::ref<x10::array::Array<x10aux::ref<x10::util::HashMap<FMGL(Key), FMGL(Value)> > > > >::template itable <x10_util_WorkerLocalStorage__closure__0<FMGL(Key),FMGL(Value) > > _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10aux::ref<x10::array::Array<x10aux::ref<x10::util::HashMap<FMGL(Key), FMGL(Value)> > > > __apply() {
        
        //#line 39 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/WorkerLocalStorage.x10": x10.ast.X10Return_c
        return x10::array::Array<x10aux::ref<x10::util::HashMap<FMGL(Key), FMGL(Value)> > >::_make(x10aux::max_threads(),
                                                                                                   x10aux::class_cast_unchecked<x10aux::ref<x10::util::HashMap<FMGL(Key), FMGL(Value)> > >(X10_NULL));
        
    }
    
    // captured environment
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        x10_util_WorkerLocalStorage__closure__0<FMGL(Key),FMGL(Value) >* storage = x10aux::alloc<x10_util_WorkerLocalStorage__closure__0<FMGL(Key),FMGL(Value) > >();
        buf.record_reference(x10aux::ref<x10_util_WorkerLocalStorage__closure__0<FMGL(Key),FMGL(Value) > >(storage));
        x10aux::ref<x10_util_WorkerLocalStorage__closure__0<FMGL(Key),FMGL(Value) > > this_ = new (storage) x10_util_WorkerLocalStorage__closure__0<FMGL(Key),FMGL(Value) >();
        return this_;
    }
    
    x10_util_WorkerLocalStorage__closure__0() { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_0<x10aux::ref<x10::array::Array<x10aux::ref<x10::util::HashMap<FMGL(Key), FMGL(Value)> > > > > >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_0<x10aux::ref<x10::array::Array<x10aux::ref<x10::util::HashMap<FMGL(Key), FMGL(Value)> > > > > >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/WorkerLocalStorage.x10:39";
    }

};

template<class FMGL(Key), class FMGL(Value)> typename x10::lang::Fun_0_0<x10aux::ref<x10::array::Array<x10aux::ref<x10::util::HashMap<FMGL(Key), FMGL(Value)> > > > >::template itable <x10_util_WorkerLocalStorage__closure__0<FMGL(Key),FMGL(Value) > >x10_util_WorkerLocalStorage__closure__0<FMGL(Key),FMGL(Value) >::_itable(&x10::lang::Reference::equals, &x10::lang::Closure::hashCode, &x10_util_WorkerLocalStorage__closure__0<FMGL(Key),FMGL(Value) >::__apply, &x10_util_WorkerLocalStorage__closure__0<FMGL(Key),FMGL(Value) >::toString, &x10::lang::Closure::typeName);template<class FMGL(Key), class FMGL(Value)>
x10aux::itable_entry x10_util_WorkerLocalStorage__closure__0<FMGL(Key),FMGL(Value) >::_itables[2] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::Fun_0_0<x10aux::ref<x10::array::Array<x10aux::ref<x10::util::HashMap<FMGL(Key), FMGL(Value)> > > > > >, &x10_util_WorkerLocalStorage__closure__0<FMGL(Key),FMGL(Value) >::_itable),x10aux::itable_entry(NULL, NULL)};

template<class FMGL(Key), class FMGL(Value)>
const x10aux::serialization_id_t x10_util_WorkerLocalStorage__closure__0<FMGL(Key),FMGL(Value) >::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(x10_util_WorkerLocalStorage__closure__0<FMGL(Key),FMGL(Value) >::template _deserialize<x10::lang::Reference>,x10aux::CLOSURE_KIND_NOT_ASYNC);

#endif // X10_UTIL_WORKERLOCALSTORAGE__CLOSURE__0_CLOSURE
#ifndef X10_UTIL_WORKERLOCALSTORAGE_H_GENERICS
#define X10_UTIL_WORKERLOCALSTORAGE_H_GENERICS
template<class FMGL(Key), class FMGL(Value)> template<class __T> x10aux::ref<__T> x10::util::WorkerLocalStorage<FMGL(Key), FMGL(Value)>::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::util::WorkerLocalStorage<FMGL(Key), FMGL(Value)> > this_ = new (memset(x10aux::alloc<x10::util::WorkerLocalStorage<FMGL(Key), FMGL(Value)> >(), 0, sizeof(x10::util::WorkerLocalStorage<FMGL(Key), FMGL(Value)>))) x10::util::WorkerLocalStorage<FMGL(Key), FMGL(Value)>();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_UTIL_WORKERLOCALSTORAGE_H_GENERICS
#ifndef X10_UTIL_WORKERLOCALSTORAGE_H_IMPLEMENTATION
#define X10_UTIL_WORKERLOCALSTORAGE_H_IMPLEMENTATION
#include <x10/util/WorkerLocalStorage.h>


template<class FMGL(Key), class FMGL(Value)> void x10::util::WorkerLocalStorage<FMGL(Key), FMGL(Value)>::_instance_init() {
    _I_("Doing initialisation for class: x10::util::WorkerLocalStorage<FMGL(Key), FMGL(Value)>");
    
}


//#line 38 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/WorkerLocalStorage.x10": x10.ast.X10FieldDecl_c

//#line 41 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/WorkerLocalStorage.x10": x10.ast.X10MethodDecl_c
template<class FMGL(Key), class FMGL(Value)> x10aux::ref<x10::util::Box<FMGL(Value)> >
  x10::util::WorkerLocalStorage<FMGL(Key), FMGL(Value)>::get(
  FMGL(Key) key) {
    
    //#line 42 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/WorkerLocalStorage.x10": x10.ast.X10LocalDecl_c
    x10_int id = x10::lang::Runtime::workerId();
    
    //#line 43 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/WorkerLocalStorage.x10": x10.ast.X10LocalDecl_c
    x10aux::ref<x10::array::Array<x10aux::ref<x10::util::HashMap<FMGL(Key), FMGL(Value)> > > > localStore =
      x10::lang::PlaceLocalHandle_methods<x10aux::ref<x10::array::Array<x10aux::ref<x10::util::HashMap<FMGL(Key), FMGL(Value)> > > > >::__apply(((x10aux::ref<x10::util::WorkerLocalStorage<FMGL(Key), FMGL(Value)> >)this)->
                                                                                                                                                  FMGL(store));
    
    //#line 44 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/WorkerLocalStorage.x10": x10.ast.X10If_c
    if ((x10aux::struct_equals(X10_NULL, localStore->x10::array::Array<x10aux::ref<x10::util::HashMap<FMGL(Key), FMGL(Value)> > >::__apply(
                                           id))))
    {
        
        //#line 44 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/WorkerLocalStorage.x10": x10.ast.X10Return_c
        return x10aux::class_cast_unchecked<x10aux::ref<x10::util::Box<FMGL(Value)> > >(X10_NULL);
        
    }
    
    //#line 45 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/WorkerLocalStorage.x10": x10.ast.X10Return_c
    return x10aux::nullCheck(localStore->x10::array::Array<x10aux::ref<x10::util::HashMap<FMGL(Key), FMGL(Value)> > >::__apply(
                               id))->get(
             key);
    
}

//#line 48 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/WorkerLocalStorage.x10": x10.ast.X10MethodDecl_c
template<class FMGL(Key), class FMGL(Value)>
FMGL(Value)
  x10::util::WorkerLocalStorage<FMGL(Key), FMGL(Value)>::getOrElse(
  FMGL(Key) key,
  FMGL(Value) value) {
    
    //#line 49 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/WorkerLocalStorage.x10": x10.ast.X10LocalDecl_c
    x10_int id = x10::lang::Runtime::workerId();
    
    //#line 50 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/WorkerLocalStorage.x10": x10.ast.X10LocalDecl_c
    x10aux::ref<x10::array::Array<x10aux::ref<x10::util::HashMap<FMGL(Key), FMGL(Value)> > > > localStore =
      x10::lang::PlaceLocalHandle_methods<x10aux::ref<x10::array::Array<x10aux::ref<x10::util::HashMap<FMGL(Key), FMGL(Value)> > > > >::__apply(((x10aux::ref<x10::util::WorkerLocalStorage<FMGL(Key), FMGL(Value)> >)this)->
                                                                                                                                                  FMGL(store));
    
    //#line 51 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/WorkerLocalStorage.x10": x10.ast.X10If_c
    if ((x10aux::struct_equals(X10_NULL, localStore->x10::array::Array<x10aux::ref<x10::util::HashMap<FMGL(Key), FMGL(Value)> > >::__apply(
                                           id))))
    {
        
        //#line 51 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/WorkerLocalStorage.x10": x10.ast.X10Return_c
        return value;
        
    }
    
    //#line 52 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/WorkerLocalStorage.x10": x10.ast.X10Return_c
    return x10aux::nullCheck(localStore->x10::array::Array<x10aux::ref<x10::util::HashMap<FMGL(Key), FMGL(Value)> > >::__apply(
                               id))->getOrElse(
             key,
             value);
    
}

//#line 55 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/WorkerLocalStorage.x10": x10.ast.X10MethodDecl_c
template<class FMGL(Key), class FMGL(Value)>
FMGL(Value)
  x10::util::WorkerLocalStorage<FMGL(Key), FMGL(Value)>::getOrThrow(
  FMGL(Key) key) {
    
    //#line 56 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/WorkerLocalStorage.x10": x10.ast.X10LocalDecl_c
    x10_int id = x10::lang::Runtime::workerId();
    
    //#line 57 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/WorkerLocalStorage.x10": x10.ast.X10LocalDecl_c
    x10aux::ref<x10::array::Array<x10aux::ref<x10::util::HashMap<FMGL(Key), FMGL(Value)> > > > localStore =
      x10::lang::PlaceLocalHandle_methods<x10aux::ref<x10::array::Array<x10aux::ref<x10::util::HashMap<FMGL(Key), FMGL(Value)> > > > >::__apply(((x10aux::ref<x10::util::WorkerLocalStorage<FMGL(Key), FMGL(Value)> >)this)->
                                                                                                                                                  FMGL(store));
    
    //#line 58 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/WorkerLocalStorage.x10": x10.ast.X10If_c
    if ((x10aux::struct_equals(X10_NULL, localStore->x10::array::Array<x10aux::ref<x10::util::HashMap<FMGL(Key), FMGL(Value)> > >::__apply(
                                           id))))
    {
        
        //#line 58 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/WorkerLocalStorage.x10": polyglot.ast.Throw_c
        x10aux::throwException(x10aux::nullCheck(x10::util::NoSuchElementException::_make(x10aux::string_utils::lit("Not found"))));
    }
    
    //#line 59 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/WorkerLocalStorage.x10": x10.ast.X10Return_c
    return x10aux::nullCheck(localStore->x10::array::Array<x10aux::ref<x10::util::HashMap<FMGL(Key), FMGL(Value)> > >::__apply(
                               id))->getOrThrow(
             key);
    
}

//#line 62 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/WorkerLocalStorage.x10": x10.ast.X10MethodDecl_c
template<class FMGL(Key), class FMGL(Value)>
x10aux::ref<x10::util::Box<FMGL(Value)> >
  x10::util::WorkerLocalStorage<FMGL(Key), FMGL(Value)>::put(
  FMGL(Key) key,
  FMGL(Value) value) {
    
    //#line 63 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/WorkerLocalStorage.x10": x10.ast.X10LocalDecl_c
    x10_int id = x10::lang::Runtime::workerId();
    
    //#line 64 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/WorkerLocalStorage.x10": x10.ast.X10LocalDecl_c
    x10aux::ref<x10::array::Array<x10aux::ref<x10::util::HashMap<FMGL(Key), FMGL(Value)> > > > localStore =
      x10::lang::PlaceLocalHandle_methods<x10aux::ref<x10::array::Array<x10aux::ref<x10::util::HashMap<FMGL(Key), FMGL(Value)> > > > >::__apply(((x10aux::ref<x10::util::WorkerLocalStorage<FMGL(Key), FMGL(Value)> >)this)->
                                                                                                                                                  FMGL(store));
    
    //#line 65 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/WorkerLocalStorage.x10": x10.ast.X10If_c
    if ((x10aux::struct_equals(X10_NULL, localStore->x10::array::Array<x10aux::ref<x10::util::HashMap<FMGL(Key), FMGL(Value)> > >::__apply(
                                           id))))
    {
        
        //#line 65 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/WorkerLocalStorage.x10": polyglot.ast.Eval_c
        localStore->x10::array::Array<x10aux::ref<x10::util::HashMap<FMGL(Key), FMGL(Value)> > >::__set(
          x10::util::HashMap<FMGL(Key), FMGL(Value)>::_make(),
          id);
    }
    
    //#line 66 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/WorkerLocalStorage.x10": x10.ast.X10Return_c
    return x10aux::nullCheck(localStore->x10::array::Array<x10aux::ref<x10::util::HashMap<FMGL(Key), FMGL(Value)> > >::__apply(
                               id))->put(
             key,
             value);
    
}

//#line 69 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/WorkerLocalStorage.x10": x10.ast.X10MethodDecl_c
template<class FMGL(Key), class FMGL(Value)>
x10aux::ref<x10::util::Box<FMGL(Value)> >
  x10::util::WorkerLocalStorage<FMGL(Key), FMGL(Value)>::remove(
  FMGL(Key) key) {
    
    //#line 70 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/WorkerLocalStorage.x10": x10.ast.X10LocalDecl_c
    x10_int id = x10::lang::Runtime::workerId();
    
    //#line 71 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/WorkerLocalStorage.x10": x10.ast.X10LocalDecl_c
    x10aux::ref<x10::array::Array<x10aux::ref<x10::util::HashMap<FMGL(Key), FMGL(Value)> > > > localStore =
      x10::lang::PlaceLocalHandle_methods<x10aux::ref<x10::array::Array<x10aux::ref<x10::util::HashMap<FMGL(Key), FMGL(Value)> > > > >::__apply(((x10aux::ref<x10::util::WorkerLocalStorage<FMGL(Key), FMGL(Value)> >)this)->
                                                                                                                                                  FMGL(store));
    
    //#line 72 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/WorkerLocalStorage.x10": x10.ast.X10If_c
    if ((x10aux::struct_equals(X10_NULL, localStore->x10::array::Array<x10aux::ref<x10::util::HashMap<FMGL(Key), FMGL(Value)> > >::__apply(
                                           id))))
    {
        
        //#line 72 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/WorkerLocalStorage.x10": x10.ast.X10Return_c
        return x10aux::class_cast_unchecked<x10aux::ref<x10::util::Box<FMGL(Value)> > >(X10_NULL);
        
    }
    
    //#line 73 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/WorkerLocalStorage.x10": x10.ast.X10Return_c
    return x10aux::nullCheck(localStore->x10::array::Array<x10aux::ref<x10::util::HashMap<FMGL(Key), FMGL(Value)> > >::__apply(
                               id))->remove(
             key);
    
}

//#line 37 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/WorkerLocalStorage.x10": x10.ast.X10MethodDecl_c
template<class FMGL(Key), class FMGL(Value)>
x10aux::ref<x10::util::WorkerLocalStorage<FMGL(Key), FMGL(Value)> >
  x10::util::WorkerLocalStorage<FMGL(Key), FMGL(Value)>::x10__util__WorkerLocalStorage____x10__util__WorkerLocalStorage__this(
  ) {
    
    //#line 37 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/WorkerLocalStorage.x10": x10.ast.X10Return_c
    return ((x10aux::ref<x10::util::WorkerLocalStorage<FMGL(Key), FMGL(Value)> >)this);
    
}

//#line 37 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/WorkerLocalStorage.x10": x10.ast.X10ConstructorDecl_c
template<class FMGL(Key), class FMGL(Value)>
void x10::util::WorkerLocalStorage<FMGL(Key), FMGL(Value)>::_constructor(
  )
{
    this->::x10::lang::Object::_constructor();
    
    //#line 37 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/WorkerLocalStorage.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::util::WorkerLocalStorage<FMGL(Key), FMGL(Value)> >)this)->x10::util::WorkerLocalStorage<FMGL(Key), FMGL(Value)>::__fieldInitializers2235();
    
}
template<class FMGL(Key), class FMGL(Value)>
x10aux::ref<x10::util::WorkerLocalStorage<FMGL(Key), FMGL(Value)> > x10::util::WorkerLocalStorage<FMGL(Key), FMGL(Value)>::_make(
  )
{
    x10aux::ref<x10::util::WorkerLocalStorage<FMGL(Key), FMGL(Value)> > this_ = new (memset(x10aux::alloc<x10::util::WorkerLocalStorage<FMGL(Key), FMGL(Value)> >(), 0, sizeof(x10::util::WorkerLocalStorage<FMGL(Key), FMGL(Value)>))) x10::util::WorkerLocalStorage<FMGL(Key), FMGL(Value)>();
    this_->_constructor();
    return this_;
}



//#line 37 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/WorkerLocalStorage.x10": x10.ast.X10MethodDecl_c
template<class FMGL(Key), class FMGL(Value)>
void
  x10::util::WorkerLocalStorage<FMGL(Key), FMGL(Value)>::__fieldInitializers2235(
  ) {
    
    //#line 37 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/WorkerLocalStorage.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::util::WorkerLocalStorage<FMGL(Key), FMGL(Value)> >)this)->
      FMGL(store) = x10::lang::PlaceLocalHandle_methods<void>::make<x10aux::ref<x10::array::Array<x10aux::ref<x10::util::HashMap<FMGL(Key), FMGL(Value)> > > > >(
                      x10::array::Dist::makeUnique(),
                      x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Fun_0_0<x10aux::ref<x10::array::Array<x10aux::ref<x10::util::HashMap<FMGL(Key), FMGL(Value)> > > > > > >(x10aux::ref<x10::lang::Fun_0_0<x10aux::ref<x10::array::Array<x10aux::ref<x10::util::HashMap<FMGL(Key), FMGL(Value)> > > > > >(x10aux::ref<x10_util_WorkerLocalStorage__closure__0<FMGL(Key),FMGL(Value) > >(new (x10aux::alloc<x10::lang::Fun_0_0<x10aux::ref<x10::array::Array<x10aux::ref<x10::util::HashMap<FMGL(Key), FMGL(Value)> > > > > >(sizeof(x10_util_WorkerLocalStorage__closure__0<FMGL(Key),FMGL(Value)>)))x10_util_WorkerLocalStorage__closure__0<FMGL(Key),FMGL(Value)>()))));
}
template<class FMGL(Key), class FMGL(Value)>
const x10aux::serialization_id_t x10::util::WorkerLocalStorage<FMGL(Key), FMGL(Value)>::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(x10::util::WorkerLocalStorage<FMGL(Key), FMGL(Value)>::template _deserializer<x10::lang::Reference>, x10aux::CLOSURE_KIND_NOT_ASYNC);

template<class FMGL(Key), class FMGL(Value)>
void x10::util::WorkerLocalStorage<FMGL(Key), FMGL(Value)>::_serialize_body(x10aux::serialization_buffer& buf) {
    x10::lang::Object::_serialize_body(buf);
    buf.write(this->FMGL(store));
    
}

template<class FMGL(Key), class FMGL(Value)>
void x10::util::WorkerLocalStorage<FMGL(Key), FMGL(Value)>::_deserialize_body(x10aux::deserialization_buffer& buf) {
    x10::lang::Object::_deserialize_body(buf);
    FMGL(store) = buf.read<x10::lang::PlaceLocalHandle<x10aux::ref<x10::array::Array<x10aux::ref<x10::util::HashMap<FMGL(Key), FMGL(Value)> > > > > >();
}

template<class FMGL(Key), class FMGL(Value)>
x10aux::RuntimeType x10::util::WorkerLocalStorage<FMGL(Key), FMGL(Value)>::rtt;
template<class FMGL(Key), class FMGL(Value)>
void x10::util::WorkerLocalStorage<FMGL(Key), FMGL(Value)>::_initRTT() {
    const x10aux::RuntimeType *canonical = x10aux::getRTT<x10::util::WorkerLocalStorage<void, void> >();
    if (rtt.initStageOne(canonical)) return;
    const x10aux::RuntimeType* parents[1] = { x10aux::getRTT<x10::lang::Object>()};
    const x10aux::RuntimeType* params[2] = { x10aux::getRTT<FMGL(Key)>(), x10aux::getRTT<FMGL(Value)>()};
    x10aux::RuntimeType::Variance variances[2] = { x10aux::RuntimeType::invariant, x10aux::RuntimeType::invariant};
    const char *baseName = "x10.util.WorkerLocalStorage";
    rtt.initStageTwo(baseName, x10aux::RuntimeType::class_kind, 1, parents, 2, params, variances);
}
#endif // X10_UTIL_WORKERLOCALSTORAGE_H_IMPLEMENTATION
#endif // __X10_UTIL_WORKERLOCALSTORAGE_H_NODEPS
