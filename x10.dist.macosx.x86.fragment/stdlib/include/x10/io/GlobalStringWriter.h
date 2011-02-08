#ifndef __X10_IO_GLOBALSTRINGWRITER_H
#define __X10_IO_GLOBALSTRINGWRITER_H

#include <x10rt.h>


#define X10_IO_WRITER_H_NODEPS
#include <x10/io/Writer.h>
#undef X10_IO_WRITER_H_NODEPS
#define X10_LANG_GLOBALREF_STRUCT_H_NODEPS
#include <x10/lang/GlobalRef.struct_h>
#undef X10_LANG_GLOBALREF_STRUCT_H_NODEPS
namespace x10 { namespace lang { 
template<class FMGL(T)> class GlobalRef;
} } 
#include <x10/lang/GlobalRef.struct_h>
namespace x10 { namespace util { 
class StringBuilder;
} } 
namespace x10 { namespace lang { 
class Byte;
} } 
#include <x10/lang/Byte.struct_h>
namespace x10 { namespace lang { 
class Runtime;
} } 
namespace x10 { namespace lang { 
class VoidFun_0_0;
} } 
namespace x10 { namespace lang { 
class Char;
} } 
#include <x10/lang/Char.struct_h>
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace lang { 
template<class FMGL(U)> class Fun_0_0;
} } 
namespace x10 { namespace lang { 
class String;
} } 
namespace x10 { namespace lang { 
template<class FMGL(U)> class Fun_0_0;
} } 
namespace x10 { namespace io { 
template<class FMGL(T)> class Marshal;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Rail;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace io { 

class GlobalStringWriter : public x10::io::Writer   {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    x10::lang::GlobalRef<x10aux::ref<x10::util::StringBuilder> > FMGL(b);
    
    void _constructor();
    
    static x10aux::ref<x10::io::GlobalStringWriter> _make();
    
    virtual void write(x10_byte x);
    virtual x10_int size();
    virtual x10aux::ref<x10::lang::String> result();
    virtual void flush();
    virtual void close();
    virtual x10aux::ref<x10::io::GlobalStringWriter> x10__io__GlobalStringWriter____x10__io__GlobalStringWriter__this(
      );
    public: template<class FMGL(T2310)> void write(x10aux::ref<x10::io::Marshal<FMGL(T2310)> > p0, FMGL(T2310) p1);
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
#endif // X10_IO_GLOBALSTRINGWRITER_H

namespace x10 { namespace io { 
class GlobalStringWriter;
} } 

#ifndef X10_IO_GLOBALSTRINGWRITER_H_NODEPS
#define X10_IO_GLOBALSTRINGWRITER_H_NODEPS
#include <x10/io/Writer.h>
#include <x10/lang/GlobalRef.h>
#include <x10/util/StringBuilder.h>
#include <x10/lang/Byte.h>
#include <x10/lang/Runtime.h>
#include <x10/lang/VoidFun_0_0.h>
#include <x10/lang/Char.h>
#include <x10/lang/Int.h>
#include <x10/lang/Fun_0_0.h>
#include <x10/lang/String.h>
#include <x10/lang/Fun_0_0.h>
#include <x10/io/Marshal.h>
#include <x10/lang/Rail.h>
#include <x10/array/Array.h>
#ifndef X10_IO_GLOBALSTRINGWRITER_H_GENERICS
#define X10_IO_GLOBALSTRINGWRITER_H_GENERICS
template<class FMGL(T2310)> void x10::io::GlobalStringWriter::write(x10aux::ref<x10::io::Marshal<FMGL(T2310)> > p0, FMGL(T2310) p1) {
    x10::io::Writer::write<FMGL(T2310)>(p0, p1);
}
template<class __T> x10aux::ref<__T> x10::io::GlobalStringWriter::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::io::GlobalStringWriter> this_ = new (memset(x10aux::alloc<x10::io::GlobalStringWriter>(), 0, sizeof(x10::io::GlobalStringWriter))) x10::io::GlobalStringWriter();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_IO_GLOBALSTRINGWRITER_H_GENERICS
#endif // __X10_IO_GLOBALSTRINGWRITER_H_NODEPS
