#ifndef __X10_IO_PRINTER_H
#define __X10_IO_PRINTER_H

#include <x10rt.h>


#define X10_IO_FILTERWRITER_H_NODEPS
#include <x10/io/FilterWriter.h>
#undef X10_IO_FILTERWRITER_H_NODEPS
namespace x10 { namespace io { 
class Writer;
} } 
namespace x10 { namespace lang { 
class Char;
} } 
#include <x10/lang/Char.struct_h>
namespace x10 { namespace lang { 
class Lock;
} } 
namespace x10 { namespace lang { 
class Any;
} } 
namespace x10 { namespace lang { 
class String;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace lang { 
class Byte;
} } 
#include <x10/lang/Byte.struct_h>
namespace x10 { namespace io { 
class IOException;
} } 
namespace x10 { namespace io { 
class IORuntimeException;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Rail;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace io { 

class Printer : public x10::io::FilterWriter   {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    void _constructor(x10aux::ref<x10::io::Writer> w);
    
    static x10aux::ref<x10::io::Printer> _make(x10aux::ref<x10::io::Writer> w);
    
    static x10_char FMGL(NEWLINE);
    
    static void FMGL(NEWLINE__do_init)();
    static void FMGL(NEWLINE__init)();
    static volatile x10aux::status FMGL(NEWLINE__status);
    static inline x10_char FMGL(NEWLINE__get)() {
        if (FMGL(NEWLINE__status) != x10aux::INITIALIZED) {
            FMGL(NEWLINE__init)();
        }
        return x10::io::Printer::FMGL(NEWLINE);
    }
    static x10aux::ref<x10::lang::Reference> FMGL(NEWLINE__deserialize)(x10aux::deserialization_buffer &buf);
    static const x10aux::serialization_id_t FMGL(NEWLINE__id);
    
    x10aux::ref<x10::lang::Lock> FMGL(lock);
    
    virtual void println();
    virtual void println(x10aux::ref<x10::lang::Any> o);
    virtual void print(x10aux::ref<x10::lang::Any> o);
    virtual void print(x10aux::ref<x10::lang::String> s);
    virtual void printf(x10aux::ref<x10::lang::String> fmt);
    virtual void printf(x10aux::ref<x10::lang::String> fmt, x10aux::ref<x10::lang::Any> o1);
    virtual void printf(x10aux::ref<x10::lang::String> fmt, x10aux::ref<x10::lang::Any> o1,
                        x10aux::ref<x10::lang::Any> o2);
    virtual void printf(x10aux::ref<x10::lang::String> fmt, x10aux::ref<x10::lang::Any> o1,
                        x10aux::ref<x10::lang::Any> o2,
                        x10aux::ref<x10::lang::Any> o3);
    virtual void printf(x10aux::ref<x10::lang::String> fmt,
                        x10aux::ref<x10::lang::Any> o1,
                        x10aux::ref<x10::lang::Any> o2,
                        x10aux::ref<x10::lang::Any> o3,
                        x10aux::ref<x10::lang::Any> o4);
    virtual void printf(x10aux::ref<x10::lang::String> fmt,
                        x10aux::ref<x10::lang::Any> o1,
                        x10aux::ref<x10::lang::Any> o2,
                        x10aux::ref<x10::lang::Any> o3,
                        x10aux::ref<x10::lang::Any> o4,
                        x10aux::ref<x10::lang::Any> o5);
    virtual void printf(x10aux::ref<x10::lang::String> fmt,
                        x10aux::ref<x10::lang::Any> o1,
                        x10aux::ref<x10::lang::Any> o2,
                        x10aux::ref<x10::lang::Any> o3,
                        x10aux::ref<x10::lang::Any> o4,
                        x10aux::ref<x10::lang::Any> o5,
                        x10aux::ref<x10::lang::Any> o6);
    virtual void printf(x10aux::ref<x10::lang::String> fmt,
                        x10aux::ref<x10::lang::Rail<x10aux::ref<x10::lang::Any> > > args);
    virtual void printfArray(x10aux::ref<x10::lang::String> fmt,
                             x10aux::ref<x10::array::Array<x10aux::ref<x10::lang::Any> > > args);
    virtual void flush();
    virtual void close();
    virtual x10aux::ref<x10::io::Printer> x10__io__Printer____x10__io__Printer__this(
      );
    void __fieldInitializers1848();
    
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
#endif // X10_IO_PRINTER_H

namespace x10 { namespace io { 
class Printer;
} } 

#ifndef X10_IO_PRINTER_H_NODEPS
#define X10_IO_PRINTER_H_NODEPS
#include <x10/io/FilterWriter.h>
#include <x10/io/Writer.h>
#include <x10/lang/Char.h>
#include <x10/lang/Lock.h>
#include <x10/lang/Any.h>
#include <x10/lang/String.h>
#include <x10/array/Array.h>
#include <x10/lang/Byte.h>
#include <x10/io/IOException.h>
#include <x10/io/IORuntimeException.h>
#include <x10/array/Array.h>
#include <x10/lang/Rail.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/Int.h>
#ifndef X10_IO_PRINTER_H_GENERICS
#define X10_IO_PRINTER_H_GENERICS
template<class __T> x10aux::ref<__T> x10::io::Printer::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::io::Printer> this_ = new (memset(x10aux::alloc<x10::io::Printer>(), 0, sizeof(x10::io::Printer))) x10::io::Printer();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_IO_PRINTER_H_GENERICS
#endif // __X10_IO_PRINTER_H_NODEPS
