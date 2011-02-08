#ifndef __X10_LANG_INTERRUPTEDEXCEPTION_H
#define __X10_LANG_INTERRUPTEDEXCEPTION_H

#include <x10rt.h>


#define X10_LANG_EXCEPTION_H_NODEPS
#include <x10/lang/Exception.h>
#undef X10_LANG_EXCEPTION_H_NODEPS
namespace x10 { namespace lang { 

class InterruptedException : public x10::lang::Exception   {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    virtual x10aux::ref<x10::lang::InterruptedException> x10__lang__InterruptedException____x10__lang__InterruptedException__this(
      );
    void _constructor();
    
    static x10aux::ref<x10::lang::InterruptedException> _make();
    
    
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
#endif // X10_LANG_INTERRUPTEDEXCEPTION_H

namespace x10 { namespace lang { 
class InterruptedException;
} } 

#ifndef X10_LANG_INTERRUPTEDEXCEPTION_H_NODEPS
#define X10_LANG_INTERRUPTEDEXCEPTION_H_NODEPS
#include <x10/lang/Exception.h>
#ifndef X10_LANG_INTERRUPTEDEXCEPTION_H_GENERICS
#define X10_LANG_INTERRUPTEDEXCEPTION_H_GENERICS
template<class __T> x10aux::ref<__T> x10::lang::InterruptedException::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::lang::InterruptedException> this_ = new (memset(x10aux::alloc<x10::lang::InterruptedException>(), 0, sizeof(x10::lang::InterruptedException))) x10::lang::InterruptedException();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_LANG_INTERRUPTEDEXCEPTION_H_GENERICS
#endif // __X10_LANG_INTERRUPTEDEXCEPTION_H_NODEPS
