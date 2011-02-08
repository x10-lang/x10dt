#ifndef __X10_IO_PUTBACKREADER_H
#define __X10_IO_PUTBACKREADER_H

#include <x10rt.h>


#define X10_IO_FILTERREADER_H_NODEPS
#include <x10/io/FilterReader.h>
#undef X10_IO_FILTERREADER_H_NODEPS
namespace x10 { namespace util { 
template<class FMGL(T)> class GrowableRail;
} } 
namespace x10 { namespace lang { 
class Byte;
} } 
#include <x10/lang/Byte.struct_h>
namespace x10 { namespace io { 
class Reader;
} } 
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
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace io { 

class PutbackReader : public x10::io::FilterReader   {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    x10aux::ref<x10::util::GrowableRail<x10_byte > > FMGL(putback);
    
    void _constructor(x10aux::ref<x10::io::Reader> r);
    
    static x10aux::ref<x10::io::PutbackReader> _make(x10aux::ref<x10::io::Reader> r);
    
    virtual x10_byte read();
    virtual void putback(x10_byte p);
    virtual x10aux::ref<x10::io::PutbackReader> x10__io__PutbackReader____x10__io__PutbackReader__this(
      );
    public: template<class FMGL(T2315)> FMGL(T2315) read(x10aux::ref<x10::io::Marshal<FMGL(T2315)> > p0);
    public: template<class FMGL(T2316)> void read(x10aux::ref<x10::io::Marshal<FMGL(T2316)> > p0, x10aux::ref<x10::lang::Rail<FMGL(T2316) > > p1);
    public: template<class FMGL(T2317)> void read(x10aux::ref<x10::io::Marshal<FMGL(T2317)> > p0, x10aux::ref<x10::lang::Rail<FMGL(T2317) > > p1, x10_int p2, x10_int p3);
    
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
#endif // X10_IO_PUTBACKREADER_H

namespace x10 { namespace io { 
class PutbackReader;
} } 

#ifndef X10_IO_PUTBACKREADER_H_NODEPS
#define X10_IO_PUTBACKREADER_H_NODEPS
#include <x10/io/FilterReader.h>
#include <x10/util/GrowableRail.h>
#include <x10/lang/Byte.h>
#include <x10/io/Reader.h>
#include <x10/io/Marshal.h>
#include <x10/io/Marshal.h>
#include <x10/lang/Rail.h>
#include <x10/io/Marshal.h>
#include <x10/lang/Rail.h>
#include <x10/lang/Int.h>
#ifndef X10_IO_PUTBACKREADER_H_GENERICS
#define X10_IO_PUTBACKREADER_H_GENERICS
template<class FMGL(T2315)> FMGL(T2315) x10::io::PutbackReader::read(x10aux::ref<x10::io::Marshal<FMGL(T2315)> > p0) {
    return x10::io::FilterReader::read<FMGL(T2315)>(p0);
}
template<class FMGL(T2316)> void x10::io::PutbackReader::read(x10aux::ref<x10::io::Marshal<FMGL(T2316)> > p0, x10aux::ref<x10::lang::Rail<FMGL(T2316) > > p1) {
    x10::io::FilterReader::read<FMGL(T2316)>(p0, p1);
}
template<class FMGL(T2317)> void x10::io::PutbackReader::read(x10aux::ref<x10::io::Marshal<FMGL(T2317)> > p0, x10aux::ref<x10::lang::Rail<FMGL(T2317) > > p1, x10_int p2, x10_int p3) {
    x10::io::FilterReader::read<FMGL(T2317)>(p0, p1, p2, p3);
}
template<class __T> x10aux::ref<__T> x10::io::PutbackReader::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::io::PutbackReader> this_ = new (memset(x10aux::alloc<x10::io::PutbackReader>(), 0, sizeof(x10::io::PutbackReader))) x10::io::PutbackReader();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_IO_PUTBACKREADER_H_GENERICS
#endif // __X10_IO_PUTBACKREADER_H_NODEPS
