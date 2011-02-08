#ifndef __X10_IO_FILE_H
#define __X10_IO_FILE_H

#include <x10rt.h>


#define X10_LANG_OBJECT_H_NODEPS
#include <x10/lang/Object.h>
#undef X10_LANG_OBJECT_H_NODEPS
#define X10_LANG_BOOLEAN_STRUCT_H_NODEPS
#include <x10/lang/Boolean.struct_h>
#undef X10_LANG_BOOLEAN_STRUCT_H_NODEPS
namespace x10 { namespace lang { 
class Char;
} } 
#include <x10/lang/Char.struct_h>
namespace x10 { namespace lang { 
class String;
} } 
namespace x10 { namespace lang { 
class Boolean;
} } 
#include <x10/lang/Boolean.struct_h>
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace io { 
template<class FMGL(T)> class ReaderIterator;
} } 
namespace x10 { namespace io { 
template<class FMGL(T)> class ReaderIterator;
} } 
namespace x10 { namespace io { 
template<class FMGL(T)> class ReaderIterator;
} } 
namespace x10 { namespace lang { 
class Byte;
} } 
#include <x10/lang/Byte.struct_h>
namespace x10 { namespace io { 
class FileReader;
} } 
namespace x10 { namespace io { 
class FileWriter;
} } 
namespace x10 { namespace io { 
class Printer;
} } 
namespace x10 { namespace io { 
class File__NativeFile;
} } 
namespace x10 { namespace lang { 
class UnsupportedOperationException;
} } 
namespace x10 { namespace compiler { 
class Incomplete;
} } 
namespace x10 { namespace lang { 
class Long;
} } 
#include <x10/lang/Long.struct_h>
namespace x10 { namespace io { 

class File : public x10::lang::Object   {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    static x10_char FMGL(SEPARATOR);
    
    static void FMGL(SEPARATOR__do_init)();
    static void FMGL(SEPARATOR__init)();
    static volatile x10aux::status FMGL(SEPARATOR__status);
    static inline x10_char FMGL(SEPARATOR__get)() {
        if (FMGL(SEPARATOR__status) != x10aux::INITIALIZED) {
            FMGL(SEPARATOR__init)();
        }
        return x10::io::File::FMGL(SEPARATOR);
    }
    static x10aux::ref<x10::lang::Reference> FMGL(SEPARATOR__deserialize)(x10aux::deserialization_buffer &buf);
    static const x10aux::serialization_id_t FMGL(SEPARATOR__id);
    
    static x10_char FMGL(PATH_SEPARATOR);
    
    static void FMGL(PATH_SEPARATOR__do_init)();
    static void FMGL(PATH_SEPARATOR__init)();
    static volatile x10aux::status FMGL(PATH_SEPARATOR__status);
    static inline x10_char FMGL(PATH_SEPARATOR__get)() {
        if (FMGL(PATH_SEPARATOR__status) != x10aux::INITIALIZED) {
            FMGL(PATH_SEPARATOR__init)();
        }
        return x10::io::File::FMGL(PATH_SEPARATOR);
    }
    static x10aux::ref<x10::lang::Reference> FMGL(PATH_SEPARATOR__deserialize)(x10aux::deserialization_buffer &buf);
    static const x10aux::serialization_id_t FMGL(PATH_SEPARATOR__id);
    
    x10aux::ref<x10::io::File> FMGL(parent);
    
    x10aux::ref<x10::lang::String> FMGL(name);
    
    x10_boolean FMGL(absolute);
    
    void _constructor(x10aux::ref<x10::lang::String> fullName);
    
    static x10aux::ref<x10::io::File> _make(x10aux::ref<x10::lang::String> fullName);
    
    void _constructor(x10aux::ref<x10::io::File> p, x10aux::ref<x10::lang::String> n);
    
    static x10aux::ref<x10::io::File> _make(x10aux::ref<x10::io::File> p,
                                            x10aux::ref<x10::lang::String> n);
    
    virtual x10aux::ref<x10::io::ReaderIterator<x10aux::ref<x10::lang::String> > >
      lines(
      );
    virtual x10aux::ref<x10::io::ReaderIterator<x10_char> > chars(
      );
    virtual x10aux::ref<x10::io::ReaderIterator<x10_byte> > bytes(
      );
    virtual x10aux::ref<x10::io::FileReader> openRead();
    virtual x10aux::ref<x10::io::FileWriter> openWrite();
    virtual x10aux::ref<x10::io::Printer> printer();
    virtual x10aux::ref<x10::lang::String> getName();
    virtual x10aux::ref<x10::io::File> getParentFile();
    virtual x10aux::ref<x10::lang::String> getPath();
    virtual x10_boolean isAbsolute();
    virtual x10aux::ref<x10::io::File__NativeFile> nativeFile();
    virtual x10aux::ref<x10::io::File> getAbsoluteFile();
    virtual x10aux::ref<x10::io::File> getCanonicalFile();
    virtual x10_boolean exists();
    virtual x10_boolean isSymbolicLink();
    virtual x10_boolean isAlias();
    virtual x10_boolean hardLinkCount();
    virtual x10_long inodeNumber();
    virtual x10_int permissions();
    virtual x10_boolean isDirectory();
    virtual x10_boolean isFile();
    virtual x10_boolean isHidden();
    virtual x10_long lastModified();
    virtual x10_boolean setLastModified(x10_long t);
    virtual x10_long size();
    virtual x10_int compareTo(x10aux::ref<x10::io::File> id__97);
    virtual x10_boolean canRead();
    virtual x10_boolean canWrite();
    virtual x10aux::ref<x10::io::File> x10__io__File____x10__io__File__this(
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
#endif // X10_IO_FILE_H

namespace x10 { namespace io { 
class File;
} } 

#ifndef X10_IO_FILE_H_NODEPS
#define X10_IO_FILE_H_NODEPS
#include <x10/lang/Object.h>
#include <x10/lang/Char.h>
#include <x10/lang/String.h>
#include <x10/lang/Boolean.h>
#include <x10/lang/Int.h>
#include <x10/io/ReaderIterator.h>
#include <x10/io/ReaderIterator.h>
#include <x10/io/ReaderIterator.h>
#include <x10/lang/Byte.h>
#include <x10/io/FileReader.h>
#include <x10/io/FileWriter.h>
#include <x10/io/Printer.h>
#include <x10/io/File__NativeFile.h>
#include <x10/lang/UnsupportedOperationException.h>
#include <x10/compiler/Incomplete.h>
#include <x10/lang/Long.h>
#ifndef X10_IO_FILE_H_GENERICS
#define X10_IO_FILE_H_GENERICS
template<class __T> x10aux::ref<__T> x10::io::File::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::io::File> this_ = new (memset(x10aux::alloc<x10::io::File>(), 0, sizeof(x10::io::File))) x10::io::File();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_IO_FILE_H_GENERICS
#endif // __X10_IO_FILE_H_NODEPS
