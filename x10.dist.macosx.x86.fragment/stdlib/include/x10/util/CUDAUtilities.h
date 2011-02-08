#ifndef __X10_UTIL_CUDAUTILITIES_H
#define __X10_UTIL_CUDAUTILITIES_H

#include <x10rt.h>


#define X10_LANG_OBJECT_H_NODEPS
#include <x10/lang/Object.h>
#undef X10_LANG_OBJECT_H_NODEPS
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace util { 
template<class FMGL(T)> class IndexedMemoryChunk;
} } 
#include <x10/util/IndexedMemoryChunk.struct_h>
namespace x10 { namespace util { 
template<class FMGL(T)> class RemoteIndexedMemoryChunk;
} } 
#include <x10/util/RemoteIndexedMemoryChunk.struct_h>
namespace x10 { namespace lang { 
class Runtime;
} } 
namespace x10 { namespace lang { 
class FinishState;
} } 
namespace x10 { namespace lang { 
class Throwable;
} } 
namespace x10 { namespace util { 
template<class FMGL(T)> class IndexedMemoryChunk;
} } 
#include <x10/util/IndexedMemoryChunk.struct_h>
namespace x10 { namespace lang { 
class RuntimeException;
} } 
namespace x10 { namespace compiler { 
class Finalization;
} } 
namespace x10 { namespace lang { 
class Place;
} } 
#include <x10/lang/Place.struct_h>
namespace x10 { namespace util { 
template<class FMGL(T)> class IndexedMemoryChunk;
} } 
#include <x10/util/IndexedMemoryChunk.struct_h>
namespace x10 { namespace array { 
template<class FMGL(T)> class RemoteArray;
} } 
namespace x10 { namespace array { 
class Region;
} } 
namespace x10 { namespace compiler { 
class Native;
} } 
namespace x10 { namespace lang { 
class UnsupportedOperationException;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class RemoteArray;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace lang { 
class ClassCastException;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class RemoteArray;
} } 
namespace x10 { namespace lang { 
template<class FMGL(U)> class Fun_0_0;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class RemoteArray;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class RemoteArray;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class RemoteArray;
} } 
namespace x10 { namespace util { 
template<class FMGL(T)> class IndexedMemoryChunk;
} } 
#include <x10/util/IndexedMemoryChunk.struct_h>
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class RemoteArray;
} } 
namespace x10 { namespace lang { 
template<class FMGL(U)> class Fun_0_0;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class RemoteArray;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class RemoteArray;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class RemoteArray;
} } 
namespace x10 { namespace util { 
template<class FMGL(T)> class IndexedMemoryChunk;
} } 
#include <x10/util/IndexedMemoryChunk.struct_h>
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class RemoteArray;
} } 
namespace x10 { namespace lang { 
template<class FMGL(U)> class Fun_0_0;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class RemoteArray;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class RemoteArray;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class RemoteArray;
} } 
namespace x10 { namespace util { 

class CUDAUtilities : public x10::lang::Object   {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    static x10_int autoBlocks();
    static x10_int autoThreads();
    template<class FMGL(T)> static void initCUDAArray(x10::util::IndexedMemoryChunk<FMGL(T) > local,
                                                      x10::util::RemoteIndexedMemoryChunk<FMGL(T) > remote,
                                                      x10_int numElements);
    template<class FMGL(T)> static x10aux::ref<x10::array::RemoteArray<FMGL(T)> >
      makeCUDAArray(
      x10::lang::Place gpu,
      x10_int numElements,
      x10::util::IndexedMemoryChunk<FMGL(T) > init);
    template<class FMGL(T)> static x10aux::ref<x10::array::RemoteArray<FMGL(T)> >
      makeRemoteArray(
      x10::lang::Place place,
      x10_int numElements,
      x10aux::ref<x10::array::Array<FMGL(T)> > init);
    template<class FMGL(T)> static x10aux::ref<x10::array::RemoteArray<FMGL(T)> >
      makeRemoteArray(
      x10::lang::Place place,
      x10_int numElements,
      FMGL(T) init);
    template<class FMGL(T)> static x10aux::ref<x10::array::RemoteArray<FMGL(T)> >
      makeRemoteArray(
      x10::lang::Place place,
      x10_int numElements,
      x10aux::ref<x10::lang::Fun_0_1<x10_int, FMGL(T)> > init);
    template<class FMGL(T)> static void deleteRemoteArray(
      x10aux::ref<x10::array::RemoteArray<FMGL(T)> > arr);
    static x10_int mul24(x10_int a, x10_int b);
    virtual x10aux::ref<x10::util::CUDAUtilities>
      x10__util__CUDAUtilities____x10__util__CUDAUtilities__this(
      );
    void _constructor();
    
    static x10aux::ref<x10::util::CUDAUtilities> _make(
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

} } 
#endif // X10_UTIL_CUDAUTILITIES_H

namespace x10 { namespace util { 
class CUDAUtilities;
} } 

#ifndef X10_UTIL_CUDAUTILITIES_H_NODEPS
#define X10_UTIL_CUDAUTILITIES_H_NODEPS
#include <x10/lang/Object.h>
#include <x10/lang/Int.h>
#include <x10/util/IndexedMemoryChunk.h>
#include <x10/util/RemoteIndexedMemoryChunk.h>
#include <x10/lang/Runtime.h>
#include <x10/lang/FinishState.h>
#include <x10/lang/Throwable.h>
#include <x10/util/IndexedMemoryChunk.h>
#include <x10/lang/RuntimeException.h>
#include <x10/compiler/Finalization.h>
#include <x10/lang/Place.h>
#include <x10/util/IndexedMemoryChunk.h>
#include <x10/array/RemoteArray.h>
#include <x10/array/Region.h>
#include <x10/compiler/Native.h>
#include <x10/lang/UnsupportedOperationException.h>
#include <x10/array/Array.h>
#include <x10/array/RemoteArray.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/ClassCastException.h>
#include <x10/array/RemoteArray.h>
#include <x10/lang/Fun_0_0.h>
#include <x10/array/RemoteArray.h>
#include <x10/array/RemoteArray.h>
#include <x10/array/Array.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/array/RemoteArray.h>
#include <x10/util/IndexedMemoryChunk.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/array/RemoteArray.h>
#include <x10/lang/Fun_0_0.h>
#include <x10/array/RemoteArray.h>
#include <x10/array/RemoteArray.h>
#include <x10/array/Array.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/array/RemoteArray.h>
#include <x10/util/IndexedMemoryChunk.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/array/RemoteArray.h>
#include <x10/lang/Fun_0_0.h>
#include <x10/array/RemoteArray.h>
#include <x10/array/RemoteArray.h>
#include <x10/array/Array.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/array/RemoteArray.h>
#ifndef X10_UTIL_CUDAUTILITIES__CLOSURE__1_CLOSURE
#define X10_UTIL_CUDAUTILITIES__CLOSURE__1_CLOSURE
#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
template<class FMGL(T)> class x10_util_CUDAUtilities__closure__1 : public x10::lang::Closure {
    public:
    
    static typename x10::lang::Fun_0_1<x10_int, FMGL(T)>::template itable <x10_util_CUDAUtilities__closure__1<FMGL(T) > > _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    FMGL(T) __apply(x10_int p) {
        
        //#line 67 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": x10.ast.X10Return_c
        return x10aux::nullCheck(init)->x10::array::Array<FMGL(T)>::__apply(
                 p);
        
    }
    
    // captured environment
    x10aux::ref<x10::array::Array<FMGL(T)> > init;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->init);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        x10_util_CUDAUtilities__closure__1<FMGL(T) >* storage = x10aux::alloc<x10_util_CUDAUtilities__closure__1<FMGL(T) > >();
        buf.record_reference(x10aux::ref<x10_util_CUDAUtilities__closure__1<FMGL(T) > >(storage));
        x10aux::ref<x10::array::Array<FMGL(T)> > that_init = buf.read<x10aux::ref<x10::array::Array<FMGL(T)> > >();
        x10aux::ref<x10_util_CUDAUtilities__closure__1<FMGL(T) > > this_ = new (storage) x10_util_CUDAUtilities__closure__1<FMGL(T) >(that_init);
        return this_;
    }
    
    x10_util_CUDAUtilities__closure__1(x10aux::ref<x10::array::Array<FMGL(T)> > init) : init(init) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10_int, FMGL(T)> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10_int, FMGL(T)> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10:67";
    }

};

template<class FMGL(T)> typename x10::lang::Fun_0_1<x10_int, FMGL(T)>::template itable <x10_util_CUDAUtilities__closure__1<FMGL(T) > >x10_util_CUDAUtilities__closure__1<FMGL(T) >::_itable(&x10::lang::Reference::equals, &x10::lang::Closure::hashCode, &x10_util_CUDAUtilities__closure__1<FMGL(T) >::__apply, &x10_util_CUDAUtilities__closure__1<FMGL(T) >::toString, &x10::lang::Closure::typeName);template<class FMGL(T)>
x10aux::itable_entry x10_util_CUDAUtilities__closure__1<FMGL(T) >::_itables[2] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::Fun_0_1<x10_int, FMGL(T)> >, &x10_util_CUDAUtilities__closure__1<FMGL(T) >::_itable),x10aux::itable_entry(NULL, NULL)};

template<class FMGL(T)>
const x10aux::serialization_id_t x10_util_CUDAUtilities__closure__1<FMGL(T) >::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(x10_util_CUDAUtilities__closure__1<FMGL(T) >::template _deserialize<x10::lang::Reference>,x10aux::CLOSURE_KIND_NOT_ASYNC);

#endif // X10_UTIL_CUDAUTILITIES__CLOSURE__1_CLOSURE
#ifndef X10_UTIL_CUDAUTILITIES__CLOSURE__0_CLOSURE
#define X10_UTIL_CUDAUTILITIES__CLOSURE__0_CLOSURE
#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_0.h>
template<class FMGL(T)> class x10_util_CUDAUtilities__closure__0 : public x10::lang::Closure {
    public:
    
    static typename x10::lang::Fun_0_0<x10aux::ref<x10::array::RemoteArray<FMGL(T)> > >::template itable <x10_util_CUDAUtilities__closure__0<FMGL(T) > > _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10aux::ref<x10::array::RemoteArray<FMGL(T)> > __apply() {
        
        //#line 67 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": x10.ast.X10Return_c
        return x10::array::RemoteArray<FMGL(T)>::_make(x10::array::Array<FMGL(T)>::_make(numElements,
                                                                                         x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Fun_0_1<x10_int, FMGL(T)> > >(x10aux::ref<x10::lang::Fun_0_1<x10_int, FMGL(T)> >(x10aux::ref<x10_util_CUDAUtilities__closure__1<FMGL(T) > >(new (x10aux::alloc<x10::lang::Fun_0_1<x10_int, FMGL(T)> >(sizeof(x10_util_CUDAUtilities__closure__1<FMGL(T)>)))x10_util_CUDAUtilities__closure__1<FMGL(T)>(init))))));
        
    }
    
    // captured environment
    x10_int numElements;
    x10aux::ref<x10::array::Array<FMGL(T)> > init;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->numElements);
        buf.write(this->init);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        x10_util_CUDAUtilities__closure__0<FMGL(T) >* storage = x10aux::alloc<x10_util_CUDAUtilities__closure__0<FMGL(T) > >();
        buf.record_reference(x10aux::ref<x10_util_CUDAUtilities__closure__0<FMGL(T) > >(storage));
        x10_int that_numElements = buf.read<x10_int>();
        x10aux::ref<x10::array::Array<FMGL(T)> > that_init = buf.read<x10aux::ref<x10::array::Array<FMGL(T)> > >();
        x10aux::ref<x10_util_CUDAUtilities__closure__0<FMGL(T) > > this_ = new (storage) x10_util_CUDAUtilities__closure__0<FMGL(T) >(that_numElements, that_init);
        return this_;
    }
    
    x10_util_CUDAUtilities__closure__0(x10_int numElements, x10aux::ref<x10::array::Array<FMGL(T)> > init) : numElements(numElements), init(init) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_0<x10aux::ref<x10::array::RemoteArray<FMGL(T)> > > >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_0<x10aux::ref<x10::array::RemoteArray<FMGL(T)> > > >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10:67";
    }

};

template<class FMGL(T)> typename x10::lang::Fun_0_0<x10aux::ref<x10::array::RemoteArray<FMGL(T)> > >::template itable <x10_util_CUDAUtilities__closure__0<FMGL(T) > >x10_util_CUDAUtilities__closure__0<FMGL(T) >::_itable(&x10::lang::Reference::equals, &x10::lang::Closure::hashCode, &x10_util_CUDAUtilities__closure__0<FMGL(T) >::__apply, &x10_util_CUDAUtilities__closure__0<FMGL(T) >::toString, &x10::lang::Closure::typeName);template<class FMGL(T)>
x10aux::itable_entry x10_util_CUDAUtilities__closure__0<FMGL(T) >::_itables[2] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::Fun_0_0<x10aux::ref<x10::array::RemoteArray<FMGL(T)> > > >, &x10_util_CUDAUtilities__closure__0<FMGL(T) >::_itable),x10aux::itable_entry(NULL, NULL)};

template<class FMGL(T)>
const x10aux::serialization_id_t x10_util_CUDAUtilities__closure__0<FMGL(T) >::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(x10_util_CUDAUtilities__closure__0<FMGL(T) >::template _deserialize<x10::lang::Reference>,x10aux::CLOSURE_KIND_NOT_ASYNC);

#endif // X10_UTIL_CUDAUTILITIES__CLOSURE__0_CLOSURE
#ifndef X10_UTIL_CUDAUTILITIES__CLOSURE__2_CLOSURE
#define X10_UTIL_CUDAUTILITIES__CLOSURE__2_CLOSURE
#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_0.h>
template<class FMGL(T)> class x10_util_CUDAUtilities__closure__2 : public x10::lang::Closure {
    public:
    
    static typename x10::lang::Fun_0_0<x10aux::ref<x10::array::RemoteArray<FMGL(T)> > >::template itable <x10_util_CUDAUtilities__closure__2<FMGL(T) > > _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10aux::ref<x10::array::RemoteArray<FMGL(T)> > __apply() {
        
        //#line 79 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": x10.ast.X10Return_c
        return x10::array::RemoteArray<FMGL(T)>::_make(x10::array::Array<FMGL(T)>::_make(numElements,
                                                                                         init));
        
    }
    
    // captured environment
    x10_int numElements;
    FMGL(T) init;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->numElements);
        buf.write(this->init);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        x10_util_CUDAUtilities__closure__2<FMGL(T) >* storage = x10aux::alloc<x10_util_CUDAUtilities__closure__2<FMGL(T) > >();
        buf.record_reference(x10aux::ref<x10_util_CUDAUtilities__closure__2<FMGL(T) > >(storage));
        x10_int that_numElements = buf.read<x10_int>();
        FMGL(T) that_init = buf.read<FMGL(T)>();
        x10aux::ref<x10_util_CUDAUtilities__closure__2<FMGL(T) > > this_ = new (storage) x10_util_CUDAUtilities__closure__2<FMGL(T) >(that_numElements, that_init);
        return this_;
    }
    
    x10_util_CUDAUtilities__closure__2(x10_int numElements, FMGL(T) init) : numElements(numElements), init(init) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_0<x10aux::ref<x10::array::RemoteArray<FMGL(T)> > > >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_0<x10aux::ref<x10::array::RemoteArray<FMGL(T)> > > >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10:79";
    }

};

template<class FMGL(T)> typename x10::lang::Fun_0_0<x10aux::ref<x10::array::RemoteArray<FMGL(T)> > >::template itable <x10_util_CUDAUtilities__closure__2<FMGL(T) > >x10_util_CUDAUtilities__closure__2<FMGL(T) >::_itable(&x10::lang::Reference::equals, &x10::lang::Closure::hashCode, &x10_util_CUDAUtilities__closure__2<FMGL(T) >::__apply, &x10_util_CUDAUtilities__closure__2<FMGL(T) >::toString, &x10::lang::Closure::typeName);template<class FMGL(T)>
x10aux::itable_entry x10_util_CUDAUtilities__closure__2<FMGL(T) >::_itables[2] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::Fun_0_0<x10aux::ref<x10::array::RemoteArray<FMGL(T)> > > >, &x10_util_CUDAUtilities__closure__2<FMGL(T) >::_itable),x10aux::itable_entry(NULL, NULL)};

template<class FMGL(T)>
const x10aux::serialization_id_t x10_util_CUDAUtilities__closure__2<FMGL(T) >::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(x10_util_CUDAUtilities__closure__2<FMGL(T) >::template _deserialize<x10::lang::Reference>,x10aux::CLOSURE_KIND_NOT_ASYNC);

#endif // X10_UTIL_CUDAUTILITIES__CLOSURE__2_CLOSURE
#ifndef X10_UTIL_CUDAUTILITIES__CLOSURE__4_CLOSURE
#define X10_UTIL_CUDAUTILITIES__CLOSURE__4_CLOSURE
#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
template<class FMGL(T)> class x10_util_CUDAUtilities__closure__4 : public x10::lang::Closure {
    public:
    
    static typename x10::lang::Fun_0_1<x10_int, FMGL(T)>::template itable <x10_util_CUDAUtilities__closure__4<FMGL(T) > > _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    FMGL(T) __apply(x10_int p) {
        
        //#line 91 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": x10.ast.X10Return_c
        return x10::lang::Fun_0_1<x10_int, FMGL(T)>::__apply(x10aux::nullCheck(init), 
          p);
        
    }
    
    // captured environment
    x10aux::ref<x10::lang::Fun_0_1<x10_int, FMGL(T)> > init;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->init);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        x10_util_CUDAUtilities__closure__4<FMGL(T) >* storage = x10aux::alloc<x10_util_CUDAUtilities__closure__4<FMGL(T) > >();
        buf.record_reference(x10aux::ref<x10_util_CUDAUtilities__closure__4<FMGL(T) > >(storage));
        x10aux::ref<x10::lang::Fun_0_1<x10_int, FMGL(T)> > that_init = buf.read<x10aux::ref<x10::lang::Fun_0_1<x10_int, FMGL(T)> > >();
        x10aux::ref<x10_util_CUDAUtilities__closure__4<FMGL(T) > > this_ = new (storage) x10_util_CUDAUtilities__closure__4<FMGL(T) >(that_init);
        return this_;
    }
    
    x10_util_CUDAUtilities__closure__4(x10aux::ref<x10::lang::Fun_0_1<x10_int, FMGL(T)> > init) : init(init) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10_int, FMGL(T)> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10_int, FMGL(T)> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10:91";
    }

};

template<class FMGL(T)> typename x10::lang::Fun_0_1<x10_int, FMGL(T)>::template itable <x10_util_CUDAUtilities__closure__4<FMGL(T) > >x10_util_CUDAUtilities__closure__4<FMGL(T) >::_itable(&x10::lang::Reference::equals, &x10::lang::Closure::hashCode, &x10_util_CUDAUtilities__closure__4<FMGL(T) >::__apply, &x10_util_CUDAUtilities__closure__4<FMGL(T) >::toString, &x10::lang::Closure::typeName);template<class FMGL(T)>
x10aux::itable_entry x10_util_CUDAUtilities__closure__4<FMGL(T) >::_itables[2] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::Fun_0_1<x10_int, FMGL(T)> >, &x10_util_CUDAUtilities__closure__4<FMGL(T) >::_itable),x10aux::itable_entry(NULL, NULL)};

template<class FMGL(T)>
const x10aux::serialization_id_t x10_util_CUDAUtilities__closure__4<FMGL(T) >::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(x10_util_CUDAUtilities__closure__4<FMGL(T) >::template _deserialize<x10::lang::Reference>,x10aux::CLOSURE_KIND_NOT_ASYNC);

#endif // X10_UTIL_CUDAUTILITIES__CLOSURE__4_CLOSURE
#ifndef X10_UTIL_CUDAUTILITIES__CLOSURE__3_CLOSURE
#define X10_UTIL_CUDAUTILITIES__CLOSURE__3_CLOSURE
#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_0.h>
template<class FMGL(T)> class x10_util_CUDAUtilities__closure__3 : public x10::lang::Closure {
    public:
    
    static typename x10::lang::Fun_0_0<x10aux::ref<x10::array::RemoteArray<FMGL(T)> > >::template itable <x10_util_CUDAUtilities__closure__3<FMGL(T) > > _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10aux::ref<x10::array::RemoteArray<FMGL(T)> > __apply() {
        
        //#line 91 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": x10.ast.X10Return_c
        return x10::array::RemoteArray<FMGL(T)>::_make(x10::array::Array<FMGL(T)>::_make(numElements,
                                                                                         x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Fun_0_1<x10_int, FMGL(T)> > >(x10aux::ref<x10::lang::Fun_0_1<x10_int, FMGL(T)> >(x10aux::ref<x10_util_CUDAUtilities__closure__4<FMGL(T) > >(new (x10aux::alloc<x10::lang::Fun_0_1<x10_int, FMGL(T)> >(sizeof(x10_util_CUDAUtilities__closure__4<FMGL(T)>)))x10_util_CUDAUtilities__closure__4<FMGL(T)>(init))))));
        
    }
    
    // captured environment
    x10_int numElements;
    x10aux::ref<x10::lang::Fun_0_1<x10_int, FMGL(T)> > init;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->numElements);
        buf.write(this->init);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        x10_util_CUDAUtilities__closure__3<FMGL(T) >* storage = x10aux::alloc<x10_util_CUDAUtilities__closure__3<FMGL(T) > >();
        buf.record_reference(x10aux::ref<x10_util_CUDAUtilities__closure__3<FMGL(T) > >(storage));
        x10_int that_numElements = buf.read<x10_int>();
        x10aux::ref<x10::lang::Fun_0_1<x10_int, FMGL(T)> > that_init = buf.read<x10aux::ref<x10::lang::Fun_0_1<x10_int, FMGL(T)> > >();
        x10aux::ref<x10_util_CUDAUtilities__closure__3<FMGL(T) > > this_ = new (storage) x10_util_CUDAUtilities__closure__3<FMGL(T) >(that_numElements, that_init);
        return this_;
    }
    
    x10_util_CUDAUtilities__closure__3(x10_int numElements, x10aux::ref<x10::lang::Fun_0_1<x10_int, FMGL(T)> > init) : numElements(numElements), init(init) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_0<x10aux::ref<x10::array::RemoteArray<FMGL(T)> > > >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_0<x10aux::ref<x10::array::RemoteArray<FMGL(T)> > > >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10:91";
    }

};

template<class FMGL(T)> typename x10::lang::Fun_0_0<x10aux::ref<x10::array::RemoteArray<FMGL(T)> > >::template itable <x10_util_CUDAUtilities__closure__3<FMGL(T) > >x10_util_CUDAUtilities__closure__3<FMGL(T) >::_itable(&x10::lang::Reference::equals, &x10::lang::Closure::hashCode, &x10_util_CUDAUtilities__closure__3<FMGL(T) >::__apply, &x10_util_CUDAUtilities__closure__3<FMGL(T) >::toString, &x10::lang::Closure::typeName);template<class FMGL(T)>
x10aux::itable_entry x10_util_CUDAUtilities__closure__3<FMGL(T) >::_itables[2] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::Fun_0_0<x10aux::ref<x10::array::RemoteArray<FMGL(T)> > > >, &x10_util_CUDAUtilities__closure__3<FMGL(T) >::_itable),x10aux::itable_entry(NULL, NULL)};

template<class FMGL(T)>
const x10aux::serialization_id_t x10_util_CUDAUtilities__closure__3<FMGL(T) >::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(x10_util_CUDAUtilities__closure__3<FMGL(T) >::template _deserialize<x10::lang::Reference>,x10aux::CLOSURE_KIND_NOT_ASYNC);

#endif // X10_UTIL_CUDAUTILITIES__CLOSURE__3_CLOSURE
#ifndef X10_UTIL_CUDAUTILITIES_H_GENERICS
#define X10_UTIL_CUDAUTILITIES_H_GENERICS
#ifndef X10_UTIL_CUDAUTILITIES_H_initCUDAArray_1620
#define X10_UTIL_CUDAUTILITIES_H_initCUDAArray_1620
template<class FMGL(T)> void x10::util::CUDAUtilities::initCUDAArray(x10::util::IndexedMemoryChunk<FMGL(T) > local,
                                                                     x10::util::RemoteIndexedMemoryChunk<FMGL(T) > remote,
                                                                     x10_int numElements) {
    {
        
        //#line 46 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": polyglot.ast.Eval_c
        x10::lang::Runtime::ensureNotInAtomic();
        
        //#line 46 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": x10.ast.X10LocalDecl_c
        x10aux::ref<x10::lang::FinishState> x10____var10 =
          x10::lang::Runtime::startFinish();
        {
            
            //#line 46 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": x10.ast.X10LocalDecl_c
            x10aux::ref<x10::lang::Throwable> throwable2086 =
              x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Throwable> >(X10_NULL);
            
            //#line 46 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": polyglot.ast.Try_c
            try {
                
                //#line 46 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": polyglot.ast.Try_c
                try {
                    {
                        
                        //#line 46 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": polyglot.ast.Eval_c
                        x10::util::IndexedMemoryChunk<void>::asyncCopy<FMGL(T) >(local,((x10_int)0),remote,((x10_int)0),numElements);
                    }
                }
                catch (x10aux::__ref& __ref__1621) {
                    x10aux::ref<x10::lang::Throwable>& __exc__ref__1621 = (x10aux::ref<x10::lang::Throwable>&)__ref__1621;
                    if (x10aux::instanceof<x10aux::ref<x10::lang::Throwable> >(__exc__ref__1621)) {
                        x10aux::ref<x10::lang::Throwable> __lowerer__var__10__ =
                          static_cast<x10aux::ref<x10::lang::Throwable> >(__exc__ref__1621);
                        {
                            
                            //#line 46 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": polyglot.ast.Eval_c
                            x10::lang::Runtime::pushException(
                              __lowerer__var__10__);
                            
                            //#line 46 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": polyglot.ast.Throw_c
                            x10aux::throwException(x10aux::nullCheck(x10::lang::RuntimeException::_make()));
                        }
                    } else
                    throw;
                }
                
                //#line 46 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": polyglot.ast.Eval_c
                x10::compiler::Finalization::plausibleThrow();
            }
            catch (x10aux::__ref& __ref__1622) {
                x10aux::ref<x10::lang::Throwable>& __exc__ref__1622 = (x10aux::ref<x10::lang::Throwable>&)__ref__1622;
                if (x10aux::instanceof<x10aux::ref<x10::lang::Throwable> >(__exc__ref__1622)) {
                    x10aux::ref<x10::lang::Throwable> formal2087 =
                      static_cast<x10aux::ref<x10::lang::Throwable> >(__exc__ref__1622);
                    {
                        
                        //#line 46 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": polyglot.ast.Eval_c
                        throwable2086 =
                          formal2087;
                    }
                } else
                throw;
            }
            {
                
                //#line 46 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": polyglot.ast.Eval_c
                x10::lang::Runtime::stopFinish(x10____var10);
            }
            
            //#line 46 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": x10.ast.X10If_c
            if ((!x10aux::struct_equals(X10_NULL, throwable2086)))
            {
                
                //#line 46 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": x10.ast.X10If_c
                if (!(x10aux::instanceof<x10aux::ref<x10::compiler::Finalization> >(throwable2086)))
                {
                    
                    //#line 46 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": polyglot.ast.Throw_c
                    x10aux::throwException(x10aux::nullCheck(throwable2086));
                }
                
            }
            
        }
    }
}
#endif // X10_UTIL_CUDAUTILITIES_H_initCUDAArray_1620
#ifndef X10_UTIL_CUDAUTILITIES_H_makeCUDAArray_1623
#define X10_UTIL_CUDAUTILITIES_H_makeCUDAArray_1623
template<class FMGL(T)> x10aux::ref<x10::array::RemoteArray<FMGL(T)> >
  x10::util::CUDAUtilities::makeCUDAArray(
  x10::lang::Place gpu,
  x10_int numElements,
  x10::util::IndexedMemoryChunk<FMGL(T) > init) {
    
    //#line 51 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": x10.ast.X10LocalDecl_c
    x10aux::ref<x10::array::Region> reg = x10::array::Region::makeRectangular(((x10_int)0), ((x10_int) ((numElements) - (((x10_int)1)))));
    x10_ulong addr = x10aux::remote_alloc(gpu.FMGL(id), ((size_t)numElements)*sizeof(FMGL(T)));
RemoteIndexedMemoryChunk<FMGL(T)> rimc(addr, numElements, gpu);
initCUDAArray<FMGL(T)>(init,rimc,numElements);
return x10::array::RemoteArray<FMGL(T)>::_make(reg, rimc);

    
    //#line 58 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": polyglot.ast.Throw_c
    x10aux::throwException(x10aux::nullCheck(x10::lang::UnsupportedOperationException::_make()));
}
#endif // X10_UTIL_CUDAUTILITIES_H_makeCUDAArray_1623
#ifndef X10_UTIL_CUDAUTILITIES_H_makeRemoteArray_1624
#define X10_UTIL_CUDAUTILITIES_H_makeRemoteArray_1624
template<class FMGL(T)> x10aux::ref<x10::array::RemoteArray<FMGL(T)> >
  x10::util::CUDAUtilities::makeRemoteArray(
  x10::lang::Place place,
  x10_int numElements,
  x10aux::ref<x10::array::Array<FMGL(T)> > init) {
    
    //#line 64 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": x10.ast.X10If_c
    if (x10::lang::Place_methods::isCUDA(place)) {
        
        //#line 65 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": x10.ast.X10Return_c
        return x10::util::CUDAUtilities::template makeCUDAArray<FMGL(T) >(
                 place,
                 numElements,
                 x10aux::nullCheck(init)->x10::array::Array<FMGL(T)>::raw());
        
    } else {
        
        //#line 67 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": x10.ast.X10Return_c
        return (__extension__ ({
            x10aux::ref<x10::array::RemoteArray<FMGL(T)> > __desugarer__var__72__ =
              x10::lang::Runtime::template evalAt<x10aux::ref<x10::array::RemoteArray<FMGL(T)> > >(
                place,
                x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Fun_0_0<x10aux::ref<x10::array::RemoteArray<FMGL(T)> > > > >(x10aux::ref<x10::lang::Fun_0_0<x10aux::ref<x10::array::RemoteArray<FMGL(T)> > > >(x10aux::ref<x10_util_CUDAUtilities__closure__0<FMGL(T) > >(new (x10aux::alloc<x10::lang::Fun_0_0<x10aux::ref<x10::array::RemoteArray<FMGL(T)> > > >(sizeof(x10_util_CUDAUtilities__closure__0<FMGL(T)>)))x10_util_CUDAUtilities__closure__0<FMGL(T)>(numElements, init)))));
            
            //#line 67 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": x10.ast.X10LocalDecl_c
            x10aux::ref<x10::array::RemoteArray<FMGL(T)> > __var1625__;
            
            //#line 67 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": polyglot.ast.Labeled_c
            goto __ret2334; __ret2334: 
            //#line 67 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": x10.ast.X10Do_c
            do
            {
            {
                
                //#line 67 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": x10.ast.X10If_c
                if ((!x10aux::struct_equals(__desugarer__var__72__,
                                            X10_NULL)) &&
                    !((x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(__desugarer__var__72__)->
                                                                 FMGL(region))->
                                               FMGL(rank),
                                             ((x10_int)1))) &&
                      (x10aux::struct_equals(x10::lang::Place_methods::place((x10aux::nullCheck(__desugarer__var__72__)->
                                                                                FMGL(array))->location),
                                             place))))
                {
                    
                    //#line 67 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": polyglot.ast.Throw_c
                    x10aux::throwException(x10aux::nullCheck(x10::lang::ClassCastException::_make(x10aux::string_utils::lit("x10.array.RemoteArray[T]{self.region.rank==1, self.array.home==place}"))));
                }
                
                //#line 67 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": polyglot.ast.Eval_c
                __var1625__ =
                  __desugarer__var__72__;
                
                //#line 67 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": polyglot.ast.Branch_c
                goto __ret2334_end_;
            }
            goto __ret2334_next_; __ret2334_next_: ;
            }
            while (false);
            goto __ret2334_end_; __ret2334_end_: ;
            __var1625__;
        }))
        ;
        
    }
    
}
#endif // X10_UTIL_CUDAUTILITIES_H_makeRemoteArray_1624
#ifndef X10_UTIL_CUDAUTILITIES_H_makeRemoteArray_1626
#define X10_UTIL_CUDAUTILITIES_H_makeRemoteArray_1626
template<class FMGL(T)> x10aux::ref<x10::array::RemoteArray<FMGL(T)> >
  x10::util::CUDAUtilities::makeRemoteArray(
  x10::lang::Place place,
  x10_int numElements,
  FMGL(T) init) {
    
    //#line 74 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": x10.ast.X10If_c
    if (x10::lang::Place_methods::isCUDA(place))
    {
        
        //#line 75 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": x10.ast.X10LocalDecl_c
        x10::util::IndexedMemoryChunk<FMGL(T) > chunk =
          x10::util::IndexedMemoryChunk<void>::allocate<FMGL(T) >(numElements, 8, false, false);
        {
            
            //#line 76 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": x10.ast.X10LocalDecl_c
            x10_int i2079max2081 =
              ((x10_int) ((numElements) - (((x10_int)1))));
            
            //#line 76 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": polyglot.ast.For_c
            {
                x10_int i2079;
                for (
                     //#line 76 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": x10.ast.X10LocalDecl_c
                     i2079 =
                       ((x10_int)0);
                     ((i2079) <= (i2079max2081));
                     
                     //#line 76 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": polyglot.ast.Eval_c
                     i2079 =
                       ((x10_int) ((i2079) + (((x10_int)1)))))
                {
                    
                    //#line 76 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": x10.ast.X10LocalDecl_c
                    x10_int i =
                      i2079;
                    {
                        
                        //#line 76 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": polyglot.ast.Eval_c
                        (chunk)->__set(init, i);
                    }
                }
            }
            
        }
        
        //#line 77 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": x10.ast.X10Return_c
        return x10::util::CUDAUtilities::template makeCUDAArray<FMGL(T) >(
                 place,
                 numElements,
                 chunk);
        
    }
    else
    {
        
        //#line 79 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": x10.ast.X10Return_c
        return (__extension__ ({
            x10aux::ref<x10::array::RemoteArray<FMGL(T)> > __desugarer__var__73__ =
              x10::lang::Runtime::template evalAt<x10aux::ref<x10::array::RemoteArray<FMGL(T)> > >(
                place,
                x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Fun_0_0<x10aux::ref<x10::array::RemoteArray<FMGL(T)> > > > >(x10aux::ref<x10::lang::Fun_0_0<x10aux::ref<x10::array::RemoteArray<FMGL(T)> > > >(x10aux::ref<x10_util_CUDAUtilities__closure__2<FMGL(T) > >(new (x10aux::alloc<x10::lang::Fun_0_0<x10aux::ref<x10::array::RemoteArray<FMGL(T)> > > >(sizeof(x10_util_CUDAUtilities__closure__2<FMGL(T)>)))x10_util_CUDAUtilities__closure__2<FMGL(T)>(numElements, init)))));
            
            //#line 79 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": x10.ast.X10LocalDecl_c
            x10aux::ref<x10::array::RemoteArray<FMGL(T)> > __var1627__;
            
            //#line 79 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": polyglot.ast.Labeled_c
            goto __ret2335; __ret2335: 
            //#line 79 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": x10.ast.X10Do_c
            do
            {
            {
                
                //#line 79 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": x10.ast.X10If_c
                if ((!x10aux::struct_equals(__desugarer__var__73__,
                                            X10_NULL)) &&
                    !((x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(__desugarer__var__73__)->
                                                                 FMGL(region))->
                                               FMGL(rank),
                                             ((x10_int)1))) &&
                      (x10aux::struct_equals(x10::lang::Place_methods::place((x10aux::nullCheck(__desugarer__var__73__)->
                                                                                FMGL(array))->location),
                                             place))))
                {
                    
                    //#line 79 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": polyglot.ast.Throw_c
                    x10aux::throwException(x10aux::nullCheck(x10::lang::ClassCastException::_make(x10aux::string_utils::lit("x10.array.RemoteArray[T]{self.region.rank==1, self.array.home==place}"))));
                }
                
                //#line 79 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": polyglot.ast.Eval_c
                __var1627__ =
                  __desugarer__var__73__;
                
                //#line 79 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": polyglot.ast.Branch_c
                goto __ret2335_end_;
            }
            goto __ret2335_next_; __ret2335_next_: ;
            }
            while (false);
            goto __ret2335_end_; __ret2335_end_: ;
            __var1627__;
        }))
        ;
        
    }
    
}
#endif // X10_UTIL_CUDAUTILITIES_H_makeRemoteArray_1626
#ifndef X10_UTIL_CUDAUTILITIES_H_makeRemoteArray_1628
#define X10_UTIL_CUDAUTILITIES_H_makeRemoteArray_1628
template<class FMGL(T)> x10aux::ref<x10::array::RemoteArray<FMGL(T)> >
  x10::util::CUDAUtilities::makeRemoteArray(
  x10::lang::Place place,
  x10_int numElements,
  x10aux::ref<x10::lang::Fun_0_1<x10_int, FMGL(T)> > init) {
    
    //#line 86 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": x10.ast.X10If_c
    if (x10::lang::Place_methods::isCUDA(place))
    {
        
        //#line 87 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": x10.ast.X10LocalDecl_c
        x10::util::IndexedMemoryChunk<FMGL(T) > chunk =
          x10::util::IndexedMemoryChunk<void>::allocate<FMGL(T) >(numElements, 8, false, false);
        {
            
            //#line 88 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": x10.ast.X10LocalDecl_c
            x10_int i2083max2085 =
              ((x10_int) ((numElements) - (((x10_int)1))));
            
            //#line 88 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": polyglot.ast.For_c
            {
                x10_int i2083;
                for (
                     //#line 88 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": x10.ast.X10LocalDecl_c
                     i2083 =
                       ((x10_int)0);
                     ((i2083) <= (i2083max2085));
                     
                     //#line 88 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": polyglot.ast.Eval_c
                     i2083 =
                       ((x10_int) ((i2083) + (((x10_int)1)))))
                {
                    
                    //#line 88 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": x10.ast.X10LocalDecl_c
                    x10_int i =
                      i2083;
                    {
                        
                        //#line 88 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": polyglot.ast.Eval_c
                        (chunk)->__set(x10::lang::Fun_0_1<x10_int, FMGL(T)>::__apply(x10aux::nullCheck(init), 
                          i), i);
                    }
                }
            }
            
        }
        
        //#line 89 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": x10.ast.X10Return_c
        return x10::util::CUDAUtilities::template makeCUDAArray<FMGL(T) >(
                 place,
                 numElements,
                 chunk);
        
    }
    else
    {
        
        //#line 91 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": x10.ast.X10Return_c
        return (__extension__ ({
            x10aux::ref<x10::array::RemoteArray<FMGL(T)> > __desugarer__var__74__ =
              x10::lang::Runtime::template evalAt<x10aux::ref<x10::array::RemoteArray<FMGL(T)> > >(
                place,
                x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Fun_0_0<x10aux::ref<x10::array::RemoteArray<FMGL(T)> > > > >(x10aux::ref<x10::lang::Fun_0_0<x10aux::ref<x10::array::RemoteArray<FMGL(T)> > > >(x10aux::ref<x10_util_CUDAUtilities__closure__3<FMGL(T) > >(new (x10aux::alloc<x10::lang::Fun_0_0<x10aux::ref<x10::array::RemoteArray<FMGL(T)> > > >(sizeof(x10_util_CUDAUtilities__closure__3<FMGL(T)>)))x10_util_CUDAUtilities__closure__3<FMGL(T)>(numElements, init)))));
            
            //#line 91 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": x10.ast.X10LocalDecl_c
            x10aux::ref<x10::array::RemoteArray<FMGL(T)> > __var1629__;
            
            //#line 91 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": polyglot.ast.Labeled_c
            goto __ret2336; __ret2336: 
            //#line 91 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": x10.ast.X10Do_c
            do
            {
            {
                
                //#line 91 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": x10.ast.X10If_c
                if ((!x10aux::struct_equals(__desugarer__var__74__,
                                            X10_NULL)) &&
                    !((x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(__desugarer__var__74__)->
                                                                 FMGL(region))->
                                               FMGL(rank),
                                             ((x10_int)1))) &&
                      (x10aux::struct_equals(x10::lang::Place_methods::place((x10aux::nullCheck(__desugarer__var__74__)->
                                                                                FMGL(array))->location),
                                             place))))
                {
                    
                    //#line 91 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": polyglot.ast.Throw_c
                    x10aux::throwException(x10aux::nullCheck(x10::lang::ClassCastException::_make(x10aux::string_utils::lit("x10.array.RemoteArray[T]{self.region.rank==1, self.array.home==place}"))));
                }
                
                //#line 91 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": polyglot.ast.Eval_c
                __var1629__ =
                  __desugarer__var__74__;
                
                //#line 91 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": polyglot.ast.Branch_c
                goto __ret2336_end_;
            }
            goto __ret2336_next_; __ret2336_next_: ;
            }
            while (false);
            goto __ret2336_end_; __ret2336_end_: ;
            __var1629__;
        }))
        ;
        
    }
    
}
#endif // X10_UTIL_CUDAUTILITIES_H_makeRemoteArray_1628
#ifndef X10_UTIL_CUDAUTILITIES_H_deleteRemoteArray_1630
#define X10_UTIL_CUDAUTILITIES_H_deleteRemoteArray_1630
template<class FMGL(T)> void x10::util::CUDAUtilities::deleteRemoteArray(
  x10aux::ref<x10::array::RemoteArray<FMGL(T)> > arr) {
    
    //#line 97 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": x10.ast.X10LocalDecl_c
    x10::lang::Place place = x10aux::nullCheck(arr)->x10::array::RemoteArray<FMGL(T)>::home();
    
    //#line 98 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/CUDAUtilities.x10": x10.ast.X10If_c
    if (x10::lang::Place_methods::isCUDA(place))
    {
        RemoteIndexedMemoryChunk<FMGL(T)> rimc = arr->FMGL(rawData);
x10aux::remote_free(place.FMGL(id), (x10_ulong)(size_t)rimc->data);

    }
    
}
#endif // X10_UTIL_CUDAUTILITIES_H_deleteRemoteArray_1630
template<class __T> x10aux::ref<__T> x10::util::CUDAUtilities::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::util::CUDAUtilities> this_ = new (memset(x10aux::alloc<x10::util::CUDAUtilities>(), 0, sizeof(x10::util::CUDAUtilities))) x10::util::CUDAUtilities();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_UTIL_CUDAUTILITIES_H_GENERICS
#endif // __X10_UTIL_CUDAUTILITIES_H_NODEPS
