#ifndef __X10_IO_CONSOLE_H
#define __X10_IO_CONSOLE_H

#include <x10rt.h>


#define X10_LANG_OBJECT_H_NODEPS
#include <x10/lang/Object.h>
#undef X10_LANG_OBJECT_H_NODEPS
namespace x10 { namespace io { 
class OutputStreamWriter__OutputStream;
} } 
namespace x10 { namespace compiler { 
class Native;
} } 
namespace x10 { namespace io { 
class InputStreamReader__InputStream;
} } 
namespace x10 { namespace io { 
class Printer;
} } 
namespace x10 { namespace io { 
class OutputStreamWriter;
} } 
namespace x10 { namespace io { 
class Reader;
} } 
namespace x10 { namespace io { 
class InputStreamReader;
} } 
namespace x10 { namespace io { 

class Console : public x10::lang::Object   {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    static x10aux::ref<x10::io::Printer> FMGL(OUT);
    
    static void FMGL(OUT__do_init)();
    static void FMGL(OUT__init)();
    static volatile x10aux::status FMGL(OUT__status);
    static inline x10aux::ref<x10::io::Printer> FMGL(OUT__get)() {
        if (FMGL(OUT__status) != x10aux::INITIALIZED) {
            FMGL(OUT__init)();
        }
        return x10::io::Console::FMGL(OUT);
    }
    static x10aux::ref<x10::lang::Reference> FMGL(OUT__deserialize)(x10aux::deserialization_buffer &buf);
    static const x10aux::serialization_id_t FMGL(OUT__id);
    
    static x10aux::ref<x10::io::Printer> FMGL(ERR);
    
    static void FMGL(ERR__do_init)();
    static void FMGL(ERR__init)();
    static volatile x10aux::status FMGL(ERR__status);
    static inline x10aux::ref<x10::io::Printer> FMGL(ERR__get)() {
        if (FMGL(ERR__status) != x10aux::INITIALIZED) {
            FMGL(ERR__init)();
        }
        return x10::io::Console::FMGL(ERR);
    }
    static x10aux::ref<x10::lang::Reference> FMGL(ERR__deserialize)(x10aux::deserialization_buffer &buf);
    static const x10aux::serialization_id_t FMGL(ERR__id);
    
    static x10aux::ref<x10::io::Reader> FMGL(IN);
    
    static void FMGL(IN__do_init)();
    static void FMGL(IN__init)();
    static volatile x10aux::status FMGL(IN__status);
    static inline x10aux::ref<x10::io::Reader> FMGL(IN__get)() {
        if (FMGL(IN__status) != x10aux::INITIALIZED) {
            FMGL(IN__init)();
        }
        return x10::io::Console::FMGL(IN);
    }
    static x10aux::ref<x10::lang::Reference> FMGL(IN__deserialize)(x10aux::deserialization_buffer &buf);
    static const x10aux::serialization_id_t FMGL(IN__id);
    
    virtual x10aux::ref<x10::io::Console> x10__io__Console____x10__io__Console__this(
      );
    void _constructor();
    
    static x10aux::ref<x10::io::Console> _make();
    
    
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
#endif // X10_IO_CONSOLE_H

namespace x10 { namespace io { 
class Console;
} } 

#ifndef X10_IO_CONSOLE_H_NODEPS
#define X10_IO_CONSOLE_H_NODEPS
#include <x10/lang/Object.h>
#include <x10/io/OutputStreamWriter__OutputStream.h>
#include <x10/compiler/Native.h>
#include <x10/io/InputStreamReader__InputStream.h>
#include <x10/io/Printer.h>
#include <x10/io/OutputStreamWriter.h>
#include <x10/io/Reader.h>
#include <x10/io/InputStreamReader.h>
#ifndef X10_IO_CONSOLE_H_GENERICS
#define X10_IO_CONSOLE_H_GENERICS
template<class __T> x10aux::ref<__T> x10::io::Console::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::io::Console> this_ = new (memset(x10aux::alloc<x10::io::Console>(), 0, sizeof(x10::io::Console))) x10::io::Console();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_IO_CONSOLE_H_GENERICS
#endif // __X10_IO_CONSOLE_H_NODEPS
