#ifndef __X10_LANG_RUNTIMEEXCEPTION_H
#define __X10_LANG_RUNTIMEEXCEPTION_H

#include <x10rt.h>


#define X10_LANG_EXCEPTION_H_NODEPS
#include <x10/lang/Exception.h>
#undef X10_LANG_EXCEPTION_H_NODEPS
namespace x10 { namespace lang { 
class String;
} } 
namespace x10 { namespace lang { 
class Throwable;
} } 
namespace x10 { namespace lang { 

class RuntimeException : public x10::lang::Exception   {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    void _constructor();
    
    static x10aux::ref<x10::lang::RuntimeException> _make();
    
    void _constructor(x10aux::ref<x10::lang::String> message);
    
    static x10aux::ref<x10::lang::RuntimeException> _make(x10aux::ref<x10::lang::String> message);
    
    void _constructor(x10aux::ref<x10::lang::String> message, x10aux::ref<x10::lang::Throwable> cause);
    
    static x10aux::ref<x10::lang::RuntimeException> _make(x10aux::ref<x10::lang::String> message,
                                                          x10aux::ref<x10::lang::Throwable> cause);
    
    void _constructor(x10aux::ref<x10::lang::Throwable> cause);
    
    static x10aux::ref<x10::lang::RuntimeException> _make(x10aux::ref<x10::lang::Throwable> cause);
    
    virtual x10aux::ref<x10::lang::RuntimeException> x10__lang__RuntimeException____x10__lang__RuntimeException__this(
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
#endif // X10_LANG_RUNTIMEEXCEPTION_H

namespace x10 { namespace lang { 
class RuntimeException;
} } 

#ifndef X10_LANG_RUNTIMEEXCEPTION_H_NODEPS
#define X10_LANG_RUNTIMEEXCEPTION_H_NODEPS
#include <x10/lang/Exception.h>
#include <x10/lang/String.h>
#include <x10/lang/Throwable.h>
#ifndef X10_LANG_RUNTIMEEXCEPTION_H_GENERICS
#define X10_LANG_RUNTIMEEXCEPTION_H_GENERICS
template<class __T> x10aux::ref<__T> x10::lang::RuntimeException::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::lang::RuntimeException> this_ = new (memset(x10aux::alloc<x10::lang::RuntimeException>(), 0, sizeof(x10::lang::RuntimeException))) x10::lang::RuntimeException();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_LANG_RUNTIMEEXCEPTION_H_GENERICS
#endif // __X10_LANG_RUNTIMEEXCEPTION_H_NODEPS
