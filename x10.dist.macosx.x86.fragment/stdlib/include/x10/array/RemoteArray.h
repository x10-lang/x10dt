#ifndef __X10_ARRAY_REMOTEARRAY_H
#define __X10_ARRAY_REMOTEARRAY_H

#include <x10rt.h>


#define X10_LANG_OBJECT_H_NODEPS
#include <x10/lang/Object.h>
#undef X10_LANG_OBJECT_H_NODEPS
#define X10_UTIL_REMOTEINDEXEDMEMORYCHUNK_STRUCT_H_NODEPS
#include <x10/util/RemoteIndexedMemoryChunk.struct_h>
#undef X10_UTIL_REMOTEINDEXEDMEMORYCHUNK_STRUCT_H_NODEPS
#define X10_LANG_INT_STRUCT_H_NODEPS
#include <x10/lang/Int.struct_h>
#undef X10_LANG_INT_STRUCT_H_NODEPS
#define X10_LANG_GLOBALREF_STRUCT_H_NODEPS
#include <x10/lang/GlobalRef.struct_h>
#undef X10_LANG_GLOBALREF_STRUCT_H_NODEPS
namespace x10 { namespace array { 
class Region;
} } 
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace lang { 
template<class FMGL(T)> class GlobalRef;
} } 
#include <x10/lang/GlobalRef.struct_h>
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace util { 
template<class FMGL(T)> class RemoteIndexedMemoryChunk;
} } 
#include <x10/util/RemoteIndexedMemoryChunk.struct_h>
namespace x10 { namespace lang { 
class Place;
} } 
#include <x10/lang/Place.struct_h>
namespace x10 { namespace util { 
template<class FMGL(T)> class RemoteIndexedMemoryChunk;
} } 
#include <x10/util/RemoteIndexedMemoryChunk.struct_h>
namespace x10 { namespace compiler { 
class Native;
} } 
namespace x10 { namespace lang { 
class Runtime;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class GlobalRef;
} } 
#include <x10/lang/GlobalRef.struct_h>
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace lang { 
template<class FMGL(U)> class Fun_0_0;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class GlobalRef;
} } 
#include <x10/lang/GlobalRef.struct_h>
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace array { 
class Point;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace lang { 
class ClassCastException;
} } 
namespace x10 { namespace lang { 
class Any;
} } 
namespace x10 { namespace lang { 
class Boolean;
} } 
#include <x10/lang/Boolean.struct_h>
namespace x10 { namespace array { 

template<class FMGL(T)> class RemoteArray;
template <> class RemoteArray<void>;
template<class FMGL(T)> class RemoteArray : public x10::lang::Object   {
    public:
    RTT_H_DECLS_CLASS
    
    x10aux::ref<x10::array::Region> FMGL(region);
    
    x10_int FMGL(size);
    
    x10::lang::GlobalRef<x10aux::ref<x10::array::Array<FMGL(T)> > > FMGL(array);
    
    void _instance_init();
    
    x10::util::RemoteIndexedMemoryChunk<FMGL(T) > FMGL(rawData);
    
    x10_int rank();
    x10::lang::Place home();
    void _constructor(x10aux::ref<x10::array::Array<FMGL(T)> > a);
    
    static x10aux::ref<x10::array::RemoteArray<FMGL(T)> > _make(x10aux::ref<x10::array::Array<FMGL(T)> > a);
    
    void _constructor(x10aux::ref<x10::array::Region> reg, x10::util::RemoteIndexedMemoryChunk<FMGL(T) > raw);
    
    static x10aux::ref<x10::array::RemoteArray<FMGL(T)> > _make(x10aux::ref<x10::array::Region> reg,
                                                                x10::util::RemoteIndexedMemoryChunk<FMGL(T) > raw);
    
    virtual FMGL(T) __apply(x10_int i);
    virtual FMGL(T) __apply(x10aux::ref<x10::array::Point> p);
    virtual FMGL(T) __set(FMGL(T) v, x10_int i);
    virtual FMGL(T) __set(FMGL(T) v, x10aux::ref<x10::array::Point> p);
    virtual x10aux::ref<x10::array::Array<FMGL(T)> > __apply();
    virtual x10_boolean equals(x10aux::ref<x10::lang::Any> other);
    virtual x10_int hashCode();
    x10aux::ref<x10::array::Region> region();
    x10_int size();
    x10::lang::GlobalRef<x10aux::ref<x10::array::Array<FMGL(T)> > >
      array(
      );
    virtual x10aux::ref<x10::array::RemoteArray<FMGL(T)> > x10__array__RemoteArray____x10__array__RemoteArray__this(
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
template <> class RemoteArray<void> : public x10::lang::Object
{
    public:
    static x10aux::RuntimeType rtt;
    static const x10aux::RuntimeType* getRTT() { return & rtt; }
    
};

} } 
#endif // X10_ARRAY_REMOTEARRAY_H

namespace x10 { namespace array { 
template<class FMGL(T)>
class RemoteArray;
} } 

#ifndef X10_ARRAY_REMOTEARRAY_H_NODEPS
#define X10_ARRAY_REMOTEARRAY_H_NODEPS
#include <x10/lang/Object.h>
#include <x10/array/Region.h>
#include <x10/lang/Int.h>
#include <x10/lang/GlobalRef.h>
#include <x10/array/Array.h>
#include <x10/util/RemoteIndexedMemoryChunk.h>
#include <x10/lang/Place.h>
#include <x10/util/RemoteIndexedMemoryChunk.h>
#include <x10/compiler/Native.h>
#include <x10/lang/Runtime.h>
#include <x10/lang/GlobalRef.h>
#include <x10/array/Array.h>
#include <x10/lang/Fun_0_0.h>
#include <x10/lang/GlobalRef.h>
#include <x10/array/Array.h>
#include <x10/array/Point.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/ClassCastException.h>
#include <x10/lang/Any.h>
#include <x10/lang/Boolean.h>
#ifndef X10_ARRAY_REMOTEARRAY__CLOSURE__0_CLOSURE
#define X10_ARRAY_REMOTEARRAY__CLOSURE__0_CLOSURE
#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_0.h>
template<class FMGL(T)> class x10_array_RemoteArray__closure__0 : public x10::lang::Closure {
    public:
    
    static typename x10::lang::Fun_0_0<x10::lang::GlobalRef<x10aux::ref<x10::array::Array<FMGL(T)> > > >::template itable <x10_array_RemoteArray__closure__0<FMGL(T) > > _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10::lang::GlobalRef<x10aux::ref<x10::array::Array<FMGL(T)> > > __apply() {
        
        //#line 91 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RemoteArray.x10": x10.ast.X10Return_c
        return x10::lang::GlobalRef_methods<x10aux::ref<x10::array::Array<FMGL(T)> > >::_make(x10::array::Array<FMGL(T)>::_make(reg,
                                                                                                                                x10::util::IndexedMemoryChunk<FMGL(T) >((raw)->raw(), (raw)->length())));
        
    }
    
    // captured environment
    x10aux::ref<x10::array::Region> reg;
    x10::util::RemoteIndexedMemoryChunk<FMGL(T) > raw;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->reg);
        buf.write(this->raw);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        x10_array_RemoteArray__closure__0<FMGL(T) >* storage = x10aux::alloc<x10_array_RemoteArray__closure__0<FMGL(T) > >();
        buf.record_reference(x10aux::ref<x10_array_RemoteArray__closure__0<FMGL(T) > >(storage));
        x10aux::ref<x10::array::Region> that_reg = buf.read<x10aux::ref<x10::array::Region> >();
        x10::util::RemoteIndexedMemoryChunk<FMGL(T) > that_raw = buf.read<x10::util::RemoteIndexedMemoryChunk<FMGL(T) > >();
        x10aux::ref<x10_array_RemoteArray__closure__0<FMGL(T) > > this_ = new (storage) x10_array_RemoteArray__closure__0<FMGL(T) >(that_reg, that_raw);
        return this_;
    }
    
    x10_array_RemoteArray__closure__0(x10aux::ref<x10::array::Region> reg, x10::util::RemoteIndexedMemoryChunk<FMGL(T) > raw) : reg(reg), raw(raw) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_0<x10::lang::GlobalRef<x10aux::ref<x10::array::Array<FMGL(T)> > > > >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_0<x10::lang::GlobalRef<x10aux::ref<x10::array::Array<FMGL(T)> > > > >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RemoteArray.x10:91";
    }

};

template<class FMGL(T)> typename x10::lang::Fun_0_0<x10::lang::GlobalRef<x10aux::ref<x10::array::Array<FMGL(T)> > > >::template itable <x10_array_RemoteArray__closure__0<FMGL(T) > >x10_array_RemoteArray__closure__0<FMGL(T) >::_itable(&x10::lang::Reference::equals, &x10::lang::Closure::hashCode, &x10_array_RemoteArray__closure__0<FMGL(T) >::__apply, &x10_array_RemoteArray__closure__0<FMGL(T) >::toString, &x10::lang::Closure::typeName);template<class FMGL(T)>
x10aux::itable_entry x10_array_RemoteArray__closure__0<FMGL(T) >::_itables[2] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::Fun_0_0<x10::lang::GlobalRef<x10aux::ref<x10::array::Array<FMGL(T)> > > > >, &x10_array_RemoteArray__closure__0<FMGL(T) >::_itable),x10aux::itable_entry(NULL, NULL)};

template<class FMGL(T)>
const x10aux::serialization_id_t x10_array_RemoteArray__closure__0<FMGL(T) >::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(x10_array_RemoteArray__closure__0<FMGL(T) >::template _deserialize<x10::lang::Reference>,x10aux::CLOSURE_KIND_NOT_ASYNC);

#endif // X10_ARRAY_REMOTEARRAY__CLOSURE__0_CLOSURE
#ifndef X10_ARRAY_REMOTEARRAY_H_GENERICS
#define X10_ARRAY_REMOTEARRAY_H_GENERICS
template<class FMGL(T)> template<class __T> x10aux::ref<__T> x10::array::RemoteArray<FMGL(T)>::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::array::RemoteArray<FMGL(T)> > this_ = new (memset(x10aux::alloc<x10::array::RemoteArray<FMGL(T)> >(), 0, sizeof(x10::array::RemoteArray<FMGL(T)>))) x10::array::RemoteArray<FMGL(T)>();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_ARRAY_REMOTEARRAY_H_GENERICS
#ifndef X10_ARRAY_REMOTEARRAY_H_IMPLEMENTATION
#define X10_ARRAY_REMOTEARRAY_H_IMPLEMENTATION
#include <x10/array/RemoteArray.h>



//#line 37 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RemoteArray.x10": x10.ast.PropertyDecl_c

//#line 41 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RemoteArray.x10": x10.ast.PropertyDecl_c

//#line 45 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RemoteArray.x10": x10.ast.PropertyDecl_c
template<class FMGL(T)> void x10::array::RemoteArray<FMGL(T)>::_instance_init() {
    _I_("Doing initialisation for class: x10::array::RemoteArray<FMGL(T)>");
    
}


//#line 52 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RemoteArray.x10": x10.ast.X10FieldDecl_c
/**
     * Caches a remote reference to the backing storage for the remote array
     * to enable DMA operations to be initiated remotely.  
     */

//#line 57 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RemoteArray.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10_int x10::array::RemoteArray<FMGL(T)>::rank() {
    
    //#line 57 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RemoteArray.x10": x10.ast.X10Return_c
    return x10aux::nullCheck(((x10aux::ref<x10::array::RemoteArray<FMGL(T)> >)this)->
                               FMGL(region))->FMGL(rank);
    
}

//#line 62 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RemoteArray.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10::lang::Place x10::array::RemoteArray<FMGL(T)>::home(
  ) {
    
    //#line 62 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RemoteArray.x10": x10.ast.X10Return_c
    return x10::lang::Place_methods::place((((x10aux::ref<x10::array::RemoteArray<FMGL(T)> >)this)->
                                              FMGL(array))->location);
    
}

//#line 68 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RemoteArray.x10": x10.ast.X10ConstructorDecl_c
template<class FMGL(T)> void x10::array::RemoteArray<FMGL(T)>::_constructor(
                          x10aux::ref<x10::array::Array<FMGL(T)> > a)
{
    this->::x10::lang::Object::_constructor();
    {
        
        //#line 69 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RemoteArray.x10": x10.ast.AssignPropertyCall_c
        FMGL(region) = x10aux::nullCheck(a)->
                         FMGL(region);
        FMGL(size) = x10aux::nullCheck(a)->
                       FMGL(size);
        FMGL(array) = x10::lang::GlobalRef_methods<x10aux::ref<x10::array::Array<FMGL(T)> > >::_make(a);
        {
         
        }
    }
    
    //#line 70 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RemoteArray.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::array::RemoteArray<FMGL(T)> >)this)->
      FMGL(rawData) =
      x10::util::RemoteIndexedMemoryChunk<FMGL(T) >((x10aux::nullCheck(a)->x10::array::Array<FMGL(T)>::raw())->raw(), (x10aux::nullCheck(a)->x10::array::Array<FMGL(T)>::raw())->length());
    
}
template<class FMGL(T)>
x10aux::ref<x10::array::RemoteArray<FMGL(T)> > x10::array::RemoteArray<FMGL(T)>::_make(
  x10aux::ref<x10::array::Array<FMGL(T)> > a)
{
    x10aux::ref<x10::array::RemoteArray<FMGL(T)> > this_ = new (memset(x10aux::alloc<x10::array::RemoteArray<FMGL(T)> >(), 0, sizeof(x10::array::RemoteArray<FMGL(T)>))) x10::array::RemoteArray<FMGL(T)>();
    this_->_constructor(a);
    return this_;
}



//#line 85 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RemoteArray.x10": x10.ast.X10ConstructorDecl_c
template<class FMGL(T)> void x10::array::RemoteArray<FMGL(T)>::_constructor(
                          x10aux::ref<x10::array::Region> reg,
                          x10::util::RemoteIndexedMemoryChunk<FMGL(T) > raw)
{
    this->::x10::lang::Object::_constructor();
    
    //#line 86 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RemoteArray.x10": x10.ast.X10LocalDecl_c
    x10::lang::GlobalRef<x10aux::ref<x10::array::Array<FMGL(T)> > > arr;
    
    //#line 87 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RemoteArray.x10": x10.ast.X10If_c
    if (x10::lang::Place_methods::isCUDA((raw)->home))
    {}
    else
    {
        
        //#line 91 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RemoteArray.x10": polyglot.ast.Eval_c
        arr =
          x10::lang::Runtime::template evalAt<x10::lang::GlobalRef<x10aux::ref<x10::array::Array<FMGL(T)> > > >(
            (raw)->home,
            x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Fun_0_0<x10::lang::GlobalRef<x10aux::ref<x10::array::Array<FMGL(T)> > > > > >(x10aux::ref<x10::lang::Fun_0_0<x10::lang::GlobalRef<x10aux::ref<x10::array::Array<FMGL(T)> > > > >(x10aux::ref<x10_array_RemoteArray__closure__0<FMGL(T) > >(new (x10aux::alloc<x10::lang::Fun_0_0<x10::lang::GlobalRef<x10aux::ref<x10::array::Array<FMGL(T)> > > > >(sizeof(x10_array_RemoteArray__closure__0<FMGL(T)>)))x10_array_RemoteArray__closure__0<FMGL(T)>(reg, raw)))));
    }
    {
        
        //#line 93 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RemoteArray.x10": x10.ast.AssignPropertyCall_c
        FMGL(region) = reg;
        FMGL(size) = x10aux::nullCheck(reg)->size();
        FMGL(array) = arr;
        {
         
        }
    }
    
    //#line 94 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RemoteArray.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::array::RemoteArray<FMGL(T)> >)this)->
      FMGL(rawData) =
      raw;
    
}
template<class FMGL(T)>
x10aux::ref<x10::array::RemoteArray<FMGL(T)> > x10::array::RemoteArray<FMGL(T)>::_make(
  x10aux::ref<x10::array::Region> reg,
  x10::util::RemoteIndexedMemoryChunk<FMGL(T) > raw)
{
    x10aux::ref<x10::array::RemoteArray<FMGL(T)> > this_ = new (memset(x10aux::alloc<x10::array::RemoteArray<FMGL(T)> >(), 0, sizeof(x10::array::RemoteArray<FMGL(T)>))) x10::array::RemoteArray<FMGL(T)>();
    this_->_constructor(reg,
    raw);
    return this_;
}



//#line 108 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RemoteArray.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> FMGL(T) x10::array::RemoteArray<FMGL(T)>::__apply(
  x10_int i) {
    
    //#line 109 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RemoteArray.x10": x10.ast.X10Return_c
    return x10aux::nullCheck(((x10aux::ref<x10::array::RemoteArray<FMGL(T)> >)this)->__apply())->x10::array::Array<FMGL(T)>::__apply(
             i);
    
}

//#line 121 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RemoteArray.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> FMGL(T) x10::array::RemoteArray<FMGL(T)>::__apply(
  x10aux::ref<x10::array::Point> p) {
    
    //#line 121 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RemoteArray.x10": x10.ast.X10Return_c
    return x10aux::nullCheck(((x10aux::ref<x10::array::RemoteArray<FMGL(T)> >)this)->__apply())->x10::array::Array<FMGL(T)>::__apply(
             p);
    
}

//#line 136 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RemoteArray.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> FMGL(T) x10::array::RemoteArray<FMGL(T)>::__set(
  FMGL(T) v,
  x10_int i) {
    
    //#line 137 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RemoteArray.x10": x10.ast.X10Return_c
    return x10aux::nullCheck(((x10aux::ref<x10::array::RemoteArray<FMGL(T)> >)this)->__apply())->x10::array::Array<FMGL(T)>::__set(
             v,
             i);
    
}

//#line 151 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RemoteArray.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> FMGL(T) x10::array::RemoteArray<FMGL(T)>::__set(
  FMGL(T) v,
  x10aux::ref<x10::array::Point> p) {
    
    //#line 152 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RemoteArray.x10": x10.ast.X10Return_c
    return x10aux::nullCheck(((x10aux::ref<x10::array::RemoteArray<FMGL(T)> >)this)->__apply())->x10::array::Array<FMGL(T)>::__set(
             v,
             p);
    
}

//#line 159 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RemoteArray.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10aux::ref<x10::array::Array<FMGL(T)> >
  x10::array::RemoteArray<FMGL(T)>::__apply(
  ) {
    
    //#line 159 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RemoteArray.x10": x10.ast.X10Return_c
    return (__extension__ ({
        x10aux::ref<x10::array::Array<FMGL(T)> > __desugarer__var__45__ =
          (((x10aux::ref<x10::array::RemoteArray<FMGL(T)> >)this)->
             FMGL(array))->__apply();
        
        //#line 159 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RemoteArray.x10": x10.ast.X10LocalDecl_c
        x10aux::ref<x10::array::Array<FMGL(T)> > __var585__;
        
        //#line 159 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RemoteArray.x10": polyglot.ast.Labeled_c
        goto __ret2289; __ret2289: 
        //#line 159 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RemoteArray.x10": x10.ast.X10Do_c
        do
        {
        {
            
            //#line 159 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RemoteArray.x10": x10.ast.X10If_c
            if ((!x10aux::struct_equals(__desugarer__var__45__,
                                        X10_NULL)) &&
                !((x10aux::struct_equals(x10aux::nullCheck(__desugarer__var__45__)->
                                           FMGL(rank),
                                         x10aux::nullCheck(x10aux::nullCheck(((x10aux::ref<x10::array::RemoteArray<FMGL(T)> >)this))->
                                                             FMGL(region))->
                                           FMGL(rank)))))
            {
                
                //#line 159 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RemoteArray.x10": polyglot.ast.Throw_c
                x10aux::throwException(x10aux::nullCheck(x10::lang::ClassCastException::_make(x10aux::string_utils::lit("x10.array.Array[T]{self.rank==x10.array.RemoteArray#this.region.rank}"))));
            }
            
            //#line 159 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RemoteArray.x10": polyglot.ast.Eval_c
            __var585__ =
              __desugarer__var__45__;
            
            //#line 159 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RemoteArray.x10": polyglot.ast.Branch_c
            goto __ret2289_end_;
        }
        goto __ret2289_next_; __ret2289_next_: ;
        }
        while (false);
        goto __ret2289_end_; __ret2289_end_: ;
        __var585__;
    }))
    ;
    
}

//#line 161 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RemoteArray.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10_boolean x10::array::RemoteArray<FMGL(T)>::equals(
  x10aux::ref<x10::lang::Any> other) {
    
    //#line 162 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RemoteArray.x10": x10.ast.X10If_c
    if (!(x10aux::instanceof<x10aux::ref<x10::array::RemoteArray<FMGL(T)> > >(other)))
    {
        
        //#line 162 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RemoteArray.x10": x10.ast.X10Return_c
        return false;
        
    }
    
    //#line 163 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RemoteArray.x10": x10.ast.X10LocalDecl_c
    x10aux::ref<x10::array::RemoteArray<FMGL(T)> > oRA =
      x10aux::class_cast<x10aux::ref<x10::array::RemoteArray<FMGL(T)> > >(other);
    
    //#line 164 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RemoteArray.x10": x10.ast.X10Return_c
    return (x10aux::nullCheck(oRA)->FMGL(array))->equals(x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Any> >(((x10aux::ref<x10::array::RemoteArray<FMGL(T)> >)this)->
                                                                                                                      FMGL(array)));
    
}

//#line 167 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RemoteArray.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10_int x10::array::RemoteArray<FMGL(T)>::hashCode(
  ) {
    
    //#line 167 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RemoteArray.x10": x10.ast.X10Return_c
    return (((x10aux::ref<x10::array::RemoteArray<FMGL(T)> >)this)->
              FMGL(array))->hashCode();
    
}

//#line 37 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RemoteArray.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10aux::ref<x10::array::Region>
  x10::array::RemoteArray<FMGL(T)>::region(
  ) {
    
    //#line 37 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RemoteArray.x10": x10.ast.X10Return_c
    return ((x10aux::ref<x10::array::RemoteArray<FMGL(T)> >)this)->
             FMGL(region);
    
}

//#line 41 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RemoteArray.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10_int x10::array::RemoteArray<FMGL(T)>::size(
  ) {
    
    //#line 41 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RemoteArray.x10": x10.ast.X10Return_c
    return ((x10aux::ref<x10::array::RemoteArray<FMGL(T)> >)this)->
             FMGL(size);
    
}

//#line 45 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RemoteArray.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10::lang::GlobalRef<x10aux::ref<x10::array::Array<FMGL(T)> > >
  x10::array::RemoteArray<FMGL(T)>::array(
  ) {
    
    //#line 45 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RemoteArray.x10": x10.ast.X10Return_c
    return ((x10aux::ref<x10::array::RemoteArray<FMGL(T)> >)this)->
             FMGL(array);
    
}

//#line 33 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RemoteArray.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10aux::ref<x10::array::RemoteArray<FMGL(T)> >
  x10::array::RemoteArray<FMGL(T)>::x10__array__RemoteArray____x10__array__RemoteArray__this(
  ) {
    
    //#line 33 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/RemoteArray.x10": x10.ast.X10Return_c
    return ((x10aux::ref<x10::array::RemoteArray<FMGL(T)> >)this);
    
}
template<class FMGL(T)> const x10aux::serialization_id_t x10::array::RemoteArray<FMGL(T)>::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(x10::array::RemoteArray<FMGL(T)>::template _deserializer<x10::lang::Reference>, x10aux::CLOSURE_KIND_NOT_ASYNC);

template<class FMGL(T)> void x10::array::RemoteArray<FMGL(T)>::_serialize_body(x10aux::serialization_buffer& buf) {
    x10::lang::Object::_serialize_body(buf);
    buf.write(this->FMGL(rawData));
    buf.write(this->FMGL(region));
    buf.write(this->FMGL(size));
    buf.write(this->FMGL(array));
    
}

template<class FMGL(T)> void x10::array::RemoteArray<FMGL(T)>::_deserialize_body(x10aux::deserialization_buffer& buf) {
    x10::lang::Object::_deserialize_body(buf);
    FMGL(rawData) = buf.read<x10::util::RemoteIndexedMemoryChunk<FMGL(T) > >();
    FMGL(region) = buf.read<x10aux::ref<x10::array::Region> >();
    FMGL(size) = buf.read<x10_int>();
    FMGL(array) = buf.read<x10::lang::GlobalRef<x10aux::ref<x10::array::Array<FMGL(T)> > > >();
}

template<class FMGL(T)> x10aux::RuntimeType x10::array::RemoteArray<FMGL(T)>::rtt;
template<class FMGL(T)> void x10::array::RemoteArray<FMGL(T)>::_initRTT() {
    const x10aux::RuntimeType *canonical = x10aux::getRTT<x10::array::RemoteArray<void> >();
    if (rtt.initStageOne(canonical)) return;
    const x10aux::RuntimeType* parents[1] = { x10aux::getRTT<x10::lang::Object>()};
    const x10aux::RuntimeType* params[1] = { x10aux::getRTT<FMGL(T)>()};
    x10aux::RuntimeType::Variance variances[1] = { x10aux::RuntimeType::invariant};
    const char *baseName = "x10.array.RemoteArray";
    rtt.initStageTwo(baseName, x10aux::RuntimeType::class_kind, 1, parents, 1, params, variances);
}
#endif // X10_ARRAY_REMOTEARRAY_H_IMPLEMENTATION
#endif // __X10_ARRAY_REMOTEARRAY_H_NODEPS
