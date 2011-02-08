#ifndef __X10_ARRAY_UNBOUNDEDREGIONEXCEPTION_H
#define __X10_ARRAY_UNBOUNDEDREGIONEXCEPTION_H

#include <x10rt.h>


#define X10_LANG_RUNTIMEEXCEPTION_H_NODEPS
#include <x10/lang/RuntimeException.h>
#undef X10_LANG_RUNTIMEEXCEPTION_H_NODEPS
namespace x10 { namespace lang { 
class String;
} } 
namespace x10 { namespace array { 

class UnboundedRegionException : public x10::lang::RuntimeException   {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    void _constructor(x10aux::ref<x10::lang::String> msg);
    
    static x10aux::ref<x10::array::UnboundedRegionException> _make(x10aux::ref<x10::lang::String> msg);
    
    virtual x10aux::ref<x10::array::UnboundedRegionException> x10__array__UnboundedRegionException____x10__array__UnboundedRegionException__this(
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
#endif // X10_ARRAY_UNBOUNDEDREGIONEXCEPTION_H

namespace x10 { namespace array { 
class UnboundedRegionException;
} } 

#ifndef X10_ARRAY_UNBOUNDEDREGIONEXCEPTION_H_NODEPS
#define X10_ARRAY_UNBOUNDEDREGIONEXCEPTION_H_NODEPS
#include <x10/lang/RuntimeException.h>
#include <x10/lang/String.h>
#ifndef X10_ARRAY_UNBOUNDEDREGIONEXCEPTION_H_GENERICS
#define X10_ARRAY_UNBOUNDEDREGIONEXCEPTION_H_GENERICS
template<class __T> x10aux::ref<__T> x10::array::UnboundedRegionException::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::array::UnboundedRegionException> this_ = new (memset(x10aux::alloc<x10::array::UnboundedRegionException>(), 0, sizeof(x10::array::UnboundedRegionException))) x10::array::UnboundedRegionException();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_ARRAY_UNBOUNDEDREGIONEXCEPTION_H_GENERICS
#endif // __X10_ARRAY_UNBOUNDEDREGIONEXCEPTION_H_NODEPS
