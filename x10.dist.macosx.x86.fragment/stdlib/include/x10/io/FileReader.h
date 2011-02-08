#ifndef __X10_IO_FILEREADER_H
#define __X10_IO_FILEREADER_H

#include <x10rt.h>


#define X10_IO_INPUTSTREAMREADER_H_NODEPS
#include <x10/io/InputStreamReader.h>
#undef X10_IO_INPUTSTREAMREADER_H_NODEPS
namespace x10 { namespace io { 
class File;
} } 
namespace x10 { namespace io { 
class InputStreamReader__InputStream;
} } 
namespace x10 { namespace io { 
class FileReader__FileInputStream;
} } 
namespace x10 { namespace io { 

class FileReader : public x10::io::InputStreamReader   {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    x10aux::ref<x10::io::File> FMGL(file);
    
    void _constructor(x10aux::ref<x10::io::File> file);
    
    static x10aux::ref<x10::io::FileReader> _make(x10aux::ref<x10::io::File> file);
    
    virtual x10aux::ref<x10::io::FileReader> x10__io__FileReader____x10__io__FileReader__this(
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
#endif // X10_IO_FILEREADER_H

namespace x10 { namespace io { 
class FileReader;
} } 

#ifndef X10_IO_FILEREADER_H_NODEPS
#define X10_IO_FILEREADER_H_NODEPS
#include <x10/io/InputStreamReader.h>
#include <x10/io/File.h>
#include <x10/io/InputStreamReader__InputStream.h>
#include <x10/io/FileReader__FileInputStream.h>
#ifndef X10_IO_FILEREADER_H_GENERICS
#define X10_IO_FILEREADER_H_GENERICS
template<class __T> x10aux::ref<__T> x10::io::FileReader::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::io::FileReader> this_ = new (memset(x10aux::alloc<x10::io::FileReader>(), 0, sizeof(x10::io::FileReader))) x10::io::FileReader();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_IO_FILEREADER_H_GENERICS
#endif // __X10_IO_FILEREADER_H_NODEPS
