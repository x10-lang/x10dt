#ifndef __X10_IO_FILTERWRITER_H
#define __X10_IO_FILTERWRITER_H

#include <x10rt.h>


#define X10_IO_WRITER_H_NODEPS
#include <x10/io/Writer.h>
#undef X10_IO_WRITER_H_NODEPS
namespace x10 { namespace lang { 
class Byte;
} } 
#include <x10/lang/Byte.struct_h>
namespace x10 { namespace io { 
template<class FMGL(T)> class Marshal;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Rail;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace io { 

class FilterWriter : public x10::io::Writer   {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    x10aux::ref<x10::io::Writer> FMGL(w);
    
    virtual x10aux::ref<x10::io::Writer> inner();
    void _constructor(x10aux::ref<x10::io::Writer> w);
    
    static x10aux::ref<x10::io::FilterWriter> _make(x10aux::ref<x10::io::Writer> w);
    
    virtual void close();
    virtual void flush();
    virtual void write(x10_byte b);
    virtual x10aux::ref<x10::io::FilterWriter> x10__io__FilterWriter____x10__io__FilterWriter__this(
      );
    public: template<class FMGL(T2309)> void write(x10aux::ref<x10::io::Marshal<FMGL(T2309)> > p0, FMGL(T2309) p1);
    public: virtual void write(x10aux::ref<x10::lang::Rail<x10_byte > > p0);
    public: virtual void write(x10aux::ref<x10::array::Array<x10_byte> > p0);
    public: virtual void write(x10aux::ref<x10::lang::Rail<x10_byte > > p0, x10_int p1, x10_int p2);
    public: virtual void write(x10aux::ref<x10::array::Array<x10_byte> > p0, x10_int p1, x10_int p2);
    
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
#endif // X10_IO_FILTERWRITER_H

namespace x10 { namespace io { 
class FilterWriter;
} } 

#ifndef X10_IO_FILTERWRITER_H_NODEPS
#define X10_IO_FILTERWRITER_H_NODEPS
#include <x10/io/Writer.h>
#include <x10/lang/Byte.h>
#include <x10/io/Marshal.h>
#include <x10/lang/Rail.h>
#include <x10/array/Array.h>
#include <x10/lang/Int.h>
#ifndef X10_IO_FILTERWRITER_H_GENERICS
#define X10_IO_FILTERWRITER_H_GENERICS
template<class FMGL(T2309)> void x10::io::FilterWriter::write(x10aux::ref<x10::io::Marshal<FMGL(T2309)> > p0, FMGL(T2309) p1) {
    x10::io::Writer::write<FMGL(T2309)>(p0, p1);
}
template<class __T> x10aux::ref<__T> x10::io::FilterWriter::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::io::FilterWriter> this_ = new (memset(x10aux::alloc<x10::io::FilterWriter>(), 0, sizeof(x10::io::FilterWriter))) x10::io::FilterWriter();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_IO_FILTERWRITER_H_GENERICS
#endif // __X10_IO_FILTERWRITER_H_NODEPS
