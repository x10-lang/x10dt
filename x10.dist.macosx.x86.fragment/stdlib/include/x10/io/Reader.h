#ifndef __X10_IO_READER_H
#define __X10_IO_READER_H

#include <x10rt.h>


#define X10_LANG_OBJECT_H_NODEPS
#include <x10/lang/Object.h>
#undef X10_LANG_OBJECT_H_NODEPS
namespace x10 { namespace lang { 
class Byte;
} } 
#include <x10/lang/Byte.struct_h>
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace lang { 
class Boolean;
} } 
#include <x10/lang/Boolean.struct_h>
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
class String;
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
namespace x10 { namespace io { 
template<class FMGL(T)> class ReaderIterator;
} } 
namespace x10 { namespace io { 
template<class FMGL(T)> class ReaderIterator;
} } 
namespace x10 { namespace io { 
template<class FMGL(T)> class ReaderIterator;
} } 
namespace x10 { namespace io { 

class Reader : public x10::lang::Object   {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    virtual void close() = 0;
    virtual x10_byte read() = 0;
    virtual x10_int available() = 0;
    virtual void skip(x10_int id__120) = 0;
    virtual void mark(x10_int id__121) = 0;
    virtual void reset() = 0;
    virtual x10_boolean markSupported() = 0;
    virtual x10_boolean readBoolean();
    virtual x10_byte readByte();
    virtual x10_char readChar();
    virtual x10_short readShort();
    virtual x10_int readInt();
    virtual x10_long readLong();
    virtual x10_float readFloat();
    virtual x10_double readDouble();
    virtual x10aux::ref<x10::lang::String> readLine();
    template<class FMGL(T)> FMGL(T) read(x10aux::ref<x10::io::Marshal<FMGL(T)> > m);
    template<class FMGL(T)> void read(x10aux::ref<x10::io::Marshal<FMGL(T)> > m,
                                      x10aux::ref<x10::lang::Rail<FMGL(T) > > a);
    template<class FMGL(T)> void read(x10aux::ref<x10::io::Marshal<FMGL(T)> > m,
                                      x10aux::ref<x10::lang::Rail<FMGL(T) > > a,
                                      x10_int off,
                                      x10_int len);
    virtual x10aux::ref<x10::io::ReaderIterator<x10aux::ref<x10::lang::String> > >
      lines(
      );
    virtual x10aux::ref<x10::io::ReaderIterator<x10_char> > chars(
      );
    virtual x10aux::ref<x10::io::ReaderIterator<x10_byte> > bytes(
      );
    virtual x10aux::ref<x10::io::Reader> x10__io__Reader____x10__io__Reader__this(
      );
    void _constructor();
    
    
    // Serialization
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};

} } 
#endif // X10_IO_READER_H

namespace x10 { namespace io { 
class Reader;
} } 

#ifndef X10_IO_READER_H_NODEPS
#define X10_IO_READER_H_NODEPS
#include <x10/lang/Object.h>
#include <x10/lang/Byte.h>
#include <x10/lang/Int.h>
#include <x10/lang/Boolean.h>
#include <x10/io/Marshal.h>
#include <x10/lang/Char.h>
#include <x10/lang/Short.h>
#include <x10/lang/Long.h>
#include <x10/lang/Float.h>
#include <x10/lang/Double.h>
#include <x10/lang/String.h>
#include <x10/io/Marshal.h>
#include <x10/io/Marshal.h>
#include <x10/lang/Rail.h>
#include <x10/io/Marshal.h>
#include <x10/lang/Rail.h>
#include <x10/io/ReaderIterator.h>
#include <x10/io/ReaderIterator.h>
#include <x10/io/ReaderIterator.h>
#ifndef X10_IO_READER_H_GENERICS
#define X10_IO_READER_H_GENERICS
#ifndef X10_IO_READER_H_read_998
#define X10_IO_READER_H_read_998
template<class FMGL(T)> FMGL(T) x10::io::Reader::read(x10aux::ref<x10::io::Marshal<FMGL(T)> > m) {
    
    //#line 61 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/Reader.x10": x10.ast.X10Return_c
    return x10::io::Marshal<FMGL(T)>::read(x10aux::nullCheck(m), ((x10aux::ref<x10::io::Reader>)this));
    
}
#endif // X10_IO_READER_H_read_998
#ifndef X10_IO_READER_H_read_999
#define X10_IO_READER_H_read_999
template<class FMGL(T)> void x10::io::Reader::read(x10aux::ref<x10::io::Marshal<FMGL(T)> > m,
                                                   x10aux::ref<x10::lang::Rail<FMGL(T) > > a) {
    {
        
        //#line 64 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/Reader.x10": polyglot.ast.Eval_c
        ((x10aux::ref<x10::io::Reader>)this)->x10::io::Reader::template read<FMGL(T) >(
          m,
          a,
          ((x10_int)0),
          x10aux::nullCheck(a)->
            FMGL(length));
        
        //#line 64 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/Reader.x10": x10.ast.X10Return_c
        return;
    }
}
#endif // X10_IO_READER_H_read_999
#ifndef X10_IO_READER_H_read_1000
#define X10_IO_READER_H_read_1000
template<class FMGL(T)> void x10::io::Reader::read(x10aux::ref<x10::io::Marshal<FMGL(T)> > m,
                                                   x10aux::ref<x10::lang::Rail<FMGL(T) > > a,
                                                   x10_int off,
                                                   x10_int len) {
    
    //#line 67 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/Reader.x10": polyglot.ast.For_c
    {
        x10_int i;
        for (
             //#line 67 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/Reader.x10": x10.ast.X10LocalDecl_c
             i = off; ((i) < (((x10_int) ((off) + (len)))));
             
             //#line 67 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/Reader.x10": polyglot.ast.Eval_c
             i =
               ((x10_int) ((i) + (((x10_int)1)))))
        {
            
            //#line 68 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/io/Reader.x10": polyglot.ast.Eval_c
            (a)->__set(((x10aux::ref<x10::io::Reader>)this)->x10::io::Reader::template read<FMGL(T) >(
                         m), i);
        }
    }
    
}
#endif // X10_IO_READER_H_read_1000
#endif // X10_IO_READER_H_GENERICS
#endif // __X10_IO_READER_H_NODEPS
