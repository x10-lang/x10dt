#ifndef __X10_ARRAY_DISTARRAY__LOCALSTATE_H
#define __X10_ARRAY_DISTARRAY__LOCALSTATE_H

#include <x10rt.h>


#define X10_LANG_OBJECT_H_NODEPS
#include <x10/lang/Object.h>
#undef X10_LANG_OBJECT_H_NODEPS
#define X10_UTIL_INDEXEDMEMORYCHUNK_STRUCT_H_NODEPS
#include <x10/util/IndexedMemoryChunk.struct_h>
#undef X10_UTIL_INDEXEDMEMORYCHUNK_STRUCT_H_NODEPS
namespace x10 { namespace util { 
template<class FMGL(T)> class IndexedMemoryChunk;
} } 
#include <x10/util/IndexedMemoryChunk.struct_h>
namespace x10 { namespace array { 

template<class FMGL(T)> class DistArray__LocalState;
template <> class DistArray__LocalState<void>;
template<class FMGL(T)> class DistArray__LocalState : public x10::lang::Object
  {
    public:
    RTT_H_DECLS_CLASS
    
    x10::util::IndexedMemoryChunk<FMGL(T) > FMGL(data);
    
    void _instance_init();
    
    void _constructor(x10::util::IndexedMemoryChunk<FMGL(T) > c);
    
    static x10aux::ref<x10::array::DistArray__LocalState<FMGL(T)> > _make(
             x10::util::IndexedMemoryChunk<FMGL(T) > c);
    
    x10::util::IndexedMemoryChunk<FMGL(T) > data();
    virtual x10aux::ref<x10::array::DistArray__LocalState<FMGL(T)> > x10__array__DistArray__LocalState____x10__array__DistArray__LocalState__this(
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
template <> class DistArray__LocalState<void> : public x10::lang::Object
{
    public:
    static x10aux::RuntimeType rtt;
    static const x10aux::RuntimeType* getRTT() { return & rtt; }
    
};

} } 
#endif // X10_ARRAY_DISTARRAY__LOCALSTATE_H

namespace x10 { namespace array { 
template<class FMGL(T)>
class DistArray__LocalState;
} } 

#ifndef X10_ARRAY_DISTARRAY__LOCALSTATE_H_NODEPS
#define X10_ARRAY_DISTARRAY__LOCALSTATE_H_NODEPS
#include <x10/lang/Object.h>
#include <x10/util/IndexedMemoryChunk.h>
#ifndef X10_ARRAY_DISTARRAY__LOCALSTATE_H_GENERICS
#define X10_ARRAY_DISTARRAY__LOCALSTATE_H_GENERICS
template<class FMGL(T)> template<class __T> x10aux::ref<__T> x10::array::DistArray__LocalState<FMGL(T)>::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::array::DistArray__LocalState<FMGL(T)> > this_ = new (memset(x10aux::alloc<x10::array::DistArray__LocalState<FMGL(T)> >(), 0, sizeof(x10::array::DistArray__LocalState<FMGL(T)>))) x10::array::DistArray__LocalState<FMGL(T)>();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_ARRAY_DISTARRAY__LOCALSTATE_H_GENERICS
#ifndef X10_ARRAY_DISTARRAY__LOCALSTATE_H_IMPLEMENTATION
#define X10_ARRAY_DISTARRAY__LOCALSTATE_H_IMPLEMENTATION
#include <x10/array/DistArray__LocalState.h>



//#line 70 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/DistArray.x10": x10.ast.PropertyDecl_c
template<class FMGL(T)> void x10::array::DistArray__LocalState<FMGL(T)>::_instance_init() {
    _I_("Doing initialisation for class: x10::array::DistArray__LocalState<FMGL(T)>");
    
}


//#line 71 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/DistArray.x10": x10.ast.X10ConstructorDecl_c
template<class FMGL(T)> void x10::array::DistArray__LocalState<FMGL(T)>::_constructor(
                          x10::util::IndexedMemoryChunk<FMGL(T) > c)
{
    this->::x10::lang::Object::_constructor();
    {
        
        //#line 71 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/DistArray.x10": x10.ast.AssignPropertyCall_c
        FMGL(data) = c;
        {
         
        }
    }
    
}
template<class FMGL(T)>
x10aux::ref<x10::array::DistArray__LocalState<FMGL(T)> > x10::array::DistArray__LocalState<FMGL(T)>::_make(
  x10::util::IndexedMemoryChunk<FMGL(T) > c)
{
    x10aux::ref<x10::array::DistArray__LocalState<FMGL(T)> > this_ = new (memset(x10aux::alloc<x10::array::DistArray__LocalState<FMGL(T)> >(), 0, sizeof(x10::array::DistArray__LocalState<FMGL(T)>))) x10::array::DistArray__LocalState<FMGL(T)>();
    this_->_constructor(c);
    return this_;
}



//#line 70 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/DistArray.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10::util::IndexedMemoryChunk<FMGL(T) >
  x10::array::DistArray__LocalState<FMGL(T)>::data(
  ) {
    
    //#line 70 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/DistArray.x10": x10.ast.X10Return_c
    return ((x10aux::ref<x10::array::DistArray__LocalState<FMGL(T)> >)this)->
             FMGL(data);
    
}

//#line 70 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/DistArray.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10aux::ref<x10::array::DistArray__LocalState<FMGL(T)> >
  x10::array::DistArray__LocalState<FMGL(T)>::x10__array__DistArray__LocalState____x10__array__DistArray__LocalState__this(
  ) {
    
    //#line 70 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/array/DistArray.x10": x10.ast.X10Return_c
    return ((x10aux::ref<x10::array::DistArray__LocalState<FMGL(T)> >)this);
    
}
template<class FMGL(T)> const x10aux::serialization_id_t x10::array::DistArray__LocalState<FMGL(T)>::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(x10::array::DistArray__LocalState<FMGL(T)>::template _deserializer<x10::lang::Reference>, x10aux::CLOSURE_KIND_NOT_ASYNC);

template<class FMGL(T)> void x10::array::DistArray__LocalState<FMGL(T)>::_serialize_body(x10aux::serialization_buffer& buf) {
    x10::lang::Object::_serialize_body(buf);
    buf.write(this->FMGL(data));
    
}

template<class FMGL(T)> void x10::array::DistArray__LocalState<FMGL(T)>::_deserialize_body(x10aux::deserialization_buffer& buf) {
    x10::lang::Object::_deserialize_body(buf);
    FMGL(data) = buf.read<x10::util::IndexedMemoryChunk<FMGL(T) > >();
}

template<class FMGL(T)> x10aux::RuntimeType x10::array::DistArray__LocalState<FMGL(T)>::rtt;
template<class FMGL(T)> void x10::array::DistArray__LocalState<FMGL(T)>::_initRTT() {
    const x10aux::RuntimeType *canonical = x10aux::getRTT<x10::array::DistArray__LocalState<void> >();
    if (rtt.initStageOne(canonical)) return;
    const x10aux::RuntimeType* parents[1] = { x10aux::getRTT<x10::lang::Object>()};
    const x10aux::RuntimeType* params[1] = { x10aux::getRTT<FMGL(T)>()};
    x10aux::RuntimeType::Variance variances[1] = { x10aux::RuntimeType::invariant};
    const char *baseName = "x10.array.DistArray.LocalState";
    rtt.initStageTwo(baseName, x10aux::RuntimeType::class_kind, 1, parents, 1, params, variances);
}
#endif // X10_ARRAY_DISTARRAY__LOCALSTATE_H_IMPLEMENTATION
#endif // __X10_ARRAY_DISTARRAY__LOCALSTATE_H_NODEPS
