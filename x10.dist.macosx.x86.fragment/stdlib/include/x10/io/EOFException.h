#ifndef __X10_IO_EOFEXCEPTION_H
#define __X10_IO_EOFEXCEPTION_H

#include <x10rt.h>


#define X10_IO_IOEXCEPTION_H_NODEPS
#include <x10/io/IOException.h>
#undef X10_IO_IOEXCEPTION_H_NODEPS
namespace x10 { namespace lang { 
class String;
} } 
namespace x10 { namespace io { 

class EOFException : public x10::io::IOException   {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    void _constructor();
    
    static x10aux::ref<x10::io::EOFException> _make();
    
    void _constructor(x10aux::ref<x10::lang::String> message);
    
    static x10aux::ref<x10::io::EOFException> _make(x10aux::ref<x10::lang::String> message);
    
    virtual x10aux::ref<x10::io::EOFException> x10__io__EOFException____x10__io__EOFException__this(
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
#endif // X10_IO_EOFEXCEPTION_H

namespace x10 { namespace io { 
class EOFException;
} } 

#ifndef X10_IO_EOFEXCEPTION_H_NODEPS
#define X10_IO_EOFEXCEPTION_H_NODEPS
#include <x10/io/IOException.h>
#include <x10/lang/String.h>
#ifndef X10_IO_EOFEXCEPTION_H_GENERICS
#define X10_IO_EOFEXCEPTION_H_GENERICS
template<class __T> x10aux::ref<__T> x10::io::EOFException::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::io::EOFException> this_ = new (memset(x10aux::alloc<x10::io::EOFException>(), 0, sizeof(x10::io::EOFException))) x10::io::EOFException();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_IO_EOFEXCEPTION_H_GENERICS
#endif // __X10_IO_EOFEXCEPTION_H_NODEPS
