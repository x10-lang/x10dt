#ifndef __X10_IO_BYTERAILWRITER_H
#define __X10_IO_BYTERAILWRITER_H

#include <x10rt.h>


#define X10_IO_BYTEWRITER_H_NODEPS
#include <x10/io/ByteWriter.h>
#undef X10_IO_BYTEWRITER_H_NODEPS
namespace x10 { namespace lang { 
template<class FMGL(T)> class Rail;
} } 
namespace x10 { namespace lang { 
class Byte;
} } 
#include <x10/lang/Byte.struct_h>
namespace x10 { namespace util { 
template<class FMGL(Element), class FMGL(Collection)> class Builder;
} } 
namespace x10 { namespace util { 
template<class FMGL(T)> class RailBuilder;
} } 
namespace x10 { namespace io { 

class ByteRailWriter : public x10::io::ByteWriter<x10aux::ref<x10::lang::Rail<x10_byte > > >
  {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    void _constructor();
    
    static x10aux::ref<x10::io::ByteRailWriter> _make();
    
    virtual x10aux::ref<x10::lang::Rail<x10_byte > > toRail();
    virtual x10aux::ref<x10::io::ByteRailWriter> x10__io__ByteRailWriter____x10__io__ByteRailWriter__this(
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
#endif // X10_IO_BYTERAILWRITER_H

namespace x10 { namespace io { 
class ByteRailWriter;
} } 

#ifndef X10_IO_BYTERAILWRITER_H_NODEPS
#define X10_IO_BYTERAILWRITER_H_NODEPS
#include <x10/io/ByteWriter.h>
#include <x10/lang/Rail.h>
#include <x10/lang/Byte.h>
#include <x10/util/Builder.h>
#include <x10/util/RailBuilder.h>
#ifndef X10_IO_BYTERAILWRITER_H_GENERICS
#define X10_IO_BYTERAILWRITER_H_GENERICS
template<class __T> x10aux::ref<__T> x10::io::ByteRailWriter::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::io::ByteRailWriter> this_ = new (memset(x10aux::alloc<x10::io::ByteRailWriter>(), 0, sizeof(x10::io::ByteRailWriter))) x10::io::ByteRailWriter();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_IO_BYTERAILWRITER_H_GENERICS
#endif // __X10_IO_BYTERAILWRITER_H_NODEPS
