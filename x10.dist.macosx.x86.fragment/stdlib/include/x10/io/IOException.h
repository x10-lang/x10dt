#ifndef __X10_IO_IOEXCEPTION_H
#define __X10_IO_IOEXCEPTION_H

#include <x10rt.h>


#define X10_LANG_EXCEPTION_H_NODEPS
#include <x10/lang/Exception.h>
#undef X10_LANG_EXCEPTION_H_NODEPS
namespace x10 { namespace lang { 
class String;
} } 
namespace x10 { namespace io { 

class IOException : public x10::lang::Exception   {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    void _constructor();
    
    static x10aux::ref<x10::io::IOException> _make();
    
    void _constructor(x10aux::ref<x10::lang::String> message);
    
    static x10aux::ref<x10::io::IOException> _make(x10aux::ref<x10::lang::String> message);
    
    virtual x10aux::ref<x10::io::IOException> x10__io__IOException____x10__io__IOException__this(
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
#endif // X10_IO_IOEXCEPTION_H

namespace x10 { namespace io { 
class IOException;
} } 

#ifndef X10_IO_IOEXCEPTION_H_NODEPS
#define X10_IO_IOEXCEPTION_H_NODEPS
#include <x10/lang/Exception.h>
#include <x10/lang/String.h>
#ifndef X10_IO_IOEXCEPTION_H_GENERICS
#define X10_IO_IOEXCEPTION_H_GENERICS
template<class __T> x10aux::ref<__T> x10::io::IOException::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::io::IOException> this_ = new (memset(x10aux::alloc<x10::io::IOException>(), 0, sizeof(x10::io::IOException))) x10::io::IOException();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_IO_IOEXCEPTION_H_GENERICS
#endif // __X10_IO_IOEXCEPTION_H_NODEPS
