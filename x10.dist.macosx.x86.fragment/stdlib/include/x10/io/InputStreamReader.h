#ifndef __X10_IO_INPUTSTREAMREADER_H
#define __X10_IO_INPUTSTREAMREADER_H

#include <x10rt.h>


#define X10_IO_READER_H_NODEPS
#include <x10/io/Reader.h>
#undef X10_IO_READER_H_NODEPS
namespace x10 { namespace io { 
class InputStreamReader__InputStream;
} } 
namespace x10 { namespace lang { 
class Byte;
} } 
#include <x10/lang/Byte.struct_h>
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace io { 
class EOFException;
} } 
namespace x10 { namespace lang { 
class Boolean;
} } 
#include <x10/lang/Boolean.struct_h>
namespace x10 { namespace io { 
template<class FMGL(T)> class Marshal;
} } 
namespace x10 { namespace io { 
template<class FMGL(T)> class Marshal;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Rail;
} } 
namespace x10 { namespace io { 
template<class FMGL(T)> class Marshal;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Rail;
} } 
namespace x10 { namespace io { 

class InputStreamReader : public x10::io::Reader   {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    x10aux::ref<x10::io::InputStreamReader__InputStream> FMGL(stream);
    
    void _constructor(x10aux::ref<x10::io::InputStreamReader__InputStream> stream);
    
    static x10aux::ref<x10::io::InputStreamReader> _make(x10aux::ref<x10::io::InputStreamReader__InputStream> stream);
    
    virtual x10aux::ref<x10::io::InputStreamReader__InputStream> stream();
    virtual void close();
    virtual x10_byte read();
    virtual x10_int available();
    virtual void skip(x10_int off);
    virtual void mark(x10_int off);
    virtual void reset();
    virtual x10_boolean markSupported();
    virtual x10aux::ref<x10::io::InputStreamReader> x10__io__InputStreamReader____x10__io__InputStreamReader__this(
      );
    public: template<class FMGL(T2311)> FMGL(T2311) read(x10aux::ref<x10::io::Marshal<FMGL(T2311)> > p0);
    public: template<class FMGL(T2312)> void read(x10aux::ref<x10::io::Marshal<FMGL(T2312)> > p0, x10aux::ref<x10::lang::Rail<FMGL(T2312) > > p1);
    public: template<class FMGL(T2313)> void read(x10aux::ref<x10::io::Marshal<FMGL(T2313)> > p0, x10aux::ref<x10::lang::Rail<FMGL(T2313) > > p1, x10_int p2, x10_int p3);
    
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
#endif // X10_IO_INPUTSTREAMREADER_H

namespace x10 { namespace io { 
class InputStreamReader;
} } 

#ifndef X10_IO_INPUTSTREAMREADER_H_NODEPS
#define X10_IO_INPUTSTREAMREADER_H_NODEPS
#include <x10/io/Reader.h>
#include <x10/io/InputStreamReader__InputStream.h>
#include <x10/lang/Byte.h>
#include <x10/lang/Int.h>
#include <x10/io/EOFException.h>
#include <x10/lang/Boolean.h>
#include <x10/io/Marshal.h>
#include <x10/io/Marshal.h>
#include <x10/lang/Rail.h>
#include <x10/io/Marshal.h>
#include <x10/lang/Rail.h>
#ifndef X10_IO_INPUTSTREAMREADER_H_GENERICS
#define X10_IO_INPUTSTREAMREADER_H_GENERICS
template<class FMGL(T2311)> FMGL(T2311) x10::io::InputStreamReader::read(x10aux::ref<x10::io::Marshal<FMGL(T2311)> > p0) {
    return x10::io::Reader::read<FMGL(T2311)>(p0);
}
template<class FMGL(T2312)> void x10::io::InputStreamReader::read(x10aux::ref<x10::io::Marshal<FMGL(T2312)> > p0, x10aux::ref<x10::lang::Rail<FMGL(T2312) > > p1) {
    x10::io::Reader::read<FMGL(T2312)>(p0, p1);
}
template<class FMGL(T2313)> void x10::io::InputStreamReader::read(x10aux::ref<x10::io::Marshal<FMGL(T2313)> > p0, x10aux::ref<x10::lang::Rail<FMGL(T2313) > > p1, x10_int p2, x10_int p3) {
    x10::io::Reader::read<FMGL(T2313)>(p0, p1, p2, p3);
}
template<class __T> x10aux::ref<__T> x10::io::InputStreamReader::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::io::InputStreamReader> this_ = new (memset(x10aux::alloc<x10::io::InputStreamReader>(), 0, sizeof(x10::io::InputStreamReader))) x10::io::InputStreamReader();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_IO_INPUTSTREAMREADER_H_GENERICS
#endif // __X10_IO_INPUTSTREAMREADER_H_NODEPS
