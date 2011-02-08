#ifndef __X10_IO_WRITER_H
#define __X10_IO_WRITER_H

#include <x10rt.h>


#define X10_LANG_OBJECT_H_NODEPS
#include <x10/lang/Object.h>
#undef X10_LANG_OBJECT_H_NODEPS
namespace x10 { namespace lang { 
class Byte;
} } 
#include <x10/lang/Byte.struct_h>
namespace x10 { namespace io { 
template<class FMGL(T)> class Marshal;
} } 
namespace x10 { namespace lang { 
class Char;
} } 
#include <x10/lang/Char.struct_h>
namespace x10 { namespace lang { 
class Short;
} } 
#include <x10/lang/Short.struct_h>
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace lang { 
class Long;
} } 
#include <x10/lang/Long.struct_h>
namespace x10 { namespace lang { 
class Float;
} } 
#include <x10/lang/Float.struct_h>
namespace x10 { namespace lang { 
class Double;
} } 
#include <x10/lang/Double.struct_h>
namespace x10 { namespace lang { 
class Boolean;
} } 
#include <x10/lang/Boolean.struct_h>
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
class OutputStreamWriter__OutputStream;
} } 
namespace x10 { namespace compiler { 
class Native;
} } 
namespace x10 { namespace io { 

class Writer : public x10::lang::Object   {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    virtual void close() = 0;
    virtual void flush() = 0;
    virtual void write(x10_byte x) = 0;
    virtual void writeByte(x10_byte x);
    virtual void writeChar(x10_char x);
    virtual void writeShort(x10_short x);
    virtual void writeInt(x10_int x);
    virtual void writeLong(x10_long x);
    virtual void writeFloat(x10_float x);
    virtual void writeDouble(x10_double x);
    virtual void writeBoolean(x10_boolean x);
    template<class FMGL(T)> void write(x10aux::ref<x10::io::Marshal<FMGL(T)> > m,
                                       FMGL(T) x);
    virtual void write(x10aux::ref<x10::lang::Rail<x10_byte > > buf);
    virtual void write(x10aux::ref<x10::array::Array<x10_byte> > buf);
    virtual void write(x10aux::ref<x10::lang::Rail<x10_byte > > buf,
                       x10_int off,
                       x10_int len);
    virtual void write(x10aux::ref<x10::array::Array<x10_byte> > buf,
                       x10_int off,
                       x10_int len);
    x10aux::ref<x10::io::OutputStreamWriter__OutputStream> oos();
    virtual x10aux::ref<x10::io::OutputStreamWriter__OutputStream>
      getNativeOutputStream(
      );
    virtual x10aux::ref<x10::io::Writer> x10__io__Writer____x10__io__Writer__this(
      );
    void _constructor();
    
    
    // Serialization
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};

} } 
#endif // X10_IO_WRITER_H

namespace x10 { namespace io { 
class Writer;
} } 

#ifndef X10_IO_WRITER_H_NODEPS
#define X10_IO_WRITER_H_NODEPS
#include <x10/lang/Object.h>
#include <x10/lang/Byte.h>
#include <x10/io/Marshal.h>
#include <x10/lang/Char.h>
#include <x10/lang/Short.h>
#include <x10/lang/Int.h>
#include <x10/lang/Long.h>
#include <x10/lang/Float.h>
#include <x10/lang/Double.h>
#include <x10/lang/Boolean.h>
#include <x10/io/Marshal.h>
#include <x10/lang/Rail.h>
#include <x10/array/Array.h>
#include <x10/io/OutputStreamWriter__OutputStream.h>
#include <x10/compiler/Native.h>
#ifndef X10_IO_WRITER_H_GENERICS
#define X10_IO_WRITER_H_GENERICS
#ifndef X10_IO_WRITER_H_write_1037
#define X10_IO_WRITER_H_write_1037
template<class FMGL(T)> void x10::io::Writer::write(x10aux::ref<x10::io::Marshal<FMGL(T)> > m,
                                                    FMGL(T) x) {
    {
        
        //#line 54 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/Writer.x10": polyglot.ast.Eval_c
        x10::io::Marshal<FMGL(T)>::write(x10aux::nullCheck(m), ((x10aux::ref<x10::io::Writer>)this),
                                                               x);
        
        //#line 54 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/Writer.x10": x10.ast.X10Return_c
        return;
    }
}
#endif // X10_IO_WRITER_H_write_1037
#endif // X10_IO_WRITER_H_GENERICS
#endif // __X10_IO_WRITER_H_NODEPS
