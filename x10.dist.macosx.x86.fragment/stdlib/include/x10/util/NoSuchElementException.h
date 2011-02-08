#ifndef __X10_UTIL_NOSUCHELEMENTEXCEPTION_H
#define __X10_UTIL_NOSUCHELEMENTEXCEPTION_H

#include <x10rt.h>


#define X10_LANG_RUNTIMEEXCEPTION_H_NODEPS
#include <x10/lang/RuntimeException.h>
#undef X10_LANG_RUNTIMEEXCEPTION_H_NODEPS
namespace x10 { namespace lang { 
class String;
} } 
namespace x10 { namespace util { 

class NoSuchElementException : public x10::lang::RuntimeException   {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    void _constructor();
    
    static x10aux::ref<x10::util::NoSuchElementException> _make();
    
    void _constructor(x10aux::ref<x10::lang::String> message);
    
    static x10aux::ref<x10::util::NoSuchElementException> _make(x10aux::ref<x10::lang::String> message);
    
    virtual x10aux::ref<x10::util::NoSuchElementException> x10__util__NoSuchElementException____x10__util__NoSuchElementException__this(
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
#endif // X10_UTIL_NOSUCHELEMENTEXCEPTION_H

namespace x10 { namespace util { 
class NoSuchElementException;
} } 

#ifndef X10_UTIL_NOSUCHELEMENTEXCEPTION_H_NODEPS
#define X10_UTIL_NOSUCHELEMENTEXCEPTION_H_NODEPS
#include <x10/lang/RuntimeException.h>
#include <x10/lang/String.h>
#ifndef X10_UTIL_NOSUCHELEMENTEXCEPTION_H_GENERICS
#define X10_UTIL_NOSUCHELEMENTEXCEPTION_H_GENERICS
template<class __T> x10aux::ref<__T> x10::util::NoSuchElementException::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::util::NoSuchElementException> this_ = new (memset(x10aux::alloc<x10::util::NoSuchElementException>(), 0, sizeof(x10::util::NoSuchElementException))) x10::util::NoSuchElementException();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_UTIL_NOSUCHELEMENTEXCEPTION_H_GENERICS
#endif // __X10_UTIL_NOSUCHELEMENTEXCEPTION_H_NODEPS
